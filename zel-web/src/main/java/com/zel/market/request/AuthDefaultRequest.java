package com.zel.market.request;

import com.zel.commonutils.ExceptionUtil;
import com.zel.commonutils.client.HttpUtil;
import com.zel.commonutils.client.RequestUtil;
import com.zel.commonutils.client.UrlBuilder;
import com.zel.commonutils.client.UrlUtil;
import com.zel.commonutils.crypto.UuidUtils;
import com.zel.market.common.Response;
import com.zel.market.common.SysLoggers;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.dto.*;
import com.zel.market.exception.AuthException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 默认的request处理类
 */
public abstract class AuthDefaultRequest implements AuthRequest {
    protected AuthConfig config;
    protected AuthSource source;
    protected AuthStateCache authStateCache;

    public AuthDefaultRequest(AuthConfig config, AuthSource source) {
        this.config = config;
        this.source = source;

        // 校验配置合法性
    }

    public AuthDefaultRequest(AuthConfig config, AuthSource source, AuthStateCache authStateCache) {
        this.config = config;
        this.source = source;
        this.authStateCache = authStateCache;
        // 校验配置合法性
    }

    /**
     * 校验回调传回的{@code state}，为空或者不存在
     * <p>
     * {@code state}不存在的情况只有两种：
     * 1. {@code state}已使用，被正常清除
     * 2. {@code state}为前端伪造，本身就不存在
     *
     * @param state {@code state}一定不为空
     */
    public void checkState(String state) {

        if (StringUtils.isBlank(state) || !authStateCache.containsKey(state)) {
            throw new AuthException(EResponseCode.ILLEGAL_STATUS);
        }
    }

    /**
     * 返回获取accessToken的url
     *
     * @param refreshToken refreshToken
     * @return 返回获取accessToken的url
     */
    protected String refreshTokenUrl(String refreshToken) {
        return UrlBuilder.fromBaseUrl(source.refresh())
                .queryParam("client_id", config.getClientId())
                .queryParam("client_secret", config.getClientSecret())
                .queryParam("refresh_token", refreshToken)
                .queryParam("grant_type", "refresh_token")
                .queryParam("redirect_uri", config.getRedirectUri())
                .build();
    }

    /**
     * 通用的 authorizationCode 协议
     *
     * @param code code码
     * @return Response
     */
    protected String doGetAuthorizationCode(String code) {
        return HttpUtil.get(accessTokenUrl(code));
    }


    /**
     * 返回获取accessToken的url
     *
     * @param code 授权码
     * @return 返回获取accessToken的url
     */
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
                .queryParam("code", code)
                .queryParam("client_id", config.getClientId())
                .queryParam("client_secret", config.getClientSecret())
                .queryParam("grant_type", "authorization_code")
                .queryParam("redirect_uri", config.getRedirectUri())
                .build();
    }

    /**
     * 返回获取userInfo的url
     *
     * @param authToken token
     * @return 返回获取userInfo的url
     */
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo()).queryParam("access_token", authToken.getAccessToken()).build();
    }

    /**
     * 通用的 用户信息
     *
     * @param authToken token封装
     * @return Response
     */
    protected String doGetUserInfo(AuthToken authToken) {
        return HttpUtil.get(userInfoUrl(authToken));
    }


    /**
     * 通用的 authorizationCode 协议
     *
     * @param code code码
     * @return Response
     */
    protected String doPostAuthorizationCode(String code) {
        return HttpUtil.post(accessTokenUrl(code), new HashMap<>());
    }

    /**
     * 获取state，如果为空， 则默认取当前日期的时间戳
     *
     * @param state 原始的state
     * @return 返回不为null的state
     */
    protected String getRealState(String state) {
        if (StringUtils.isBlank(state)) {
            state = UuidUtils.getUUID();
        }
        // 缓存state
        authStateCache.cache(state, state);
        return state;
    }

    /**
     * 获取以 {@code separator}分割过后的 scope 信息
     *
     * @param separator     多个 {@code scope} 间的分隔符
     * @param encode        是否 encode 编码
     * @param defaultScopes 默认的 scope， 当客户端没有配置 {@code scopes} 时启用
     * @return String
     * @since 1.16.7
     */
    protected String getScopes(String separator, boolean encode, List<String> defaultScopes) {
        List<String> scopes = config.getScopes();
        if (null == scopes || scopes.isEmpty()) {
            if (null == defaultScopes || defaultScopes.isEmpty()) {
                return "";
            }
            scopes = defaultScopes;
        }
        if (null == separator) {
            // 默认为空格
            separator = " ";
        }
        String scopeStr = String.join(separator, scopes);
        return encode ? UrlUtil.urlEncode(scopeStr) : scopeStr;
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.9.3
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
                .queryParam("response_type", "code")
                .queryParam("client_id", config.getClientId())
                .queryParam("redirect_uri", config.getRedirectUri())
                .queryParam("state", getRealState(state))
                .build();
    }

    /**
     * 获取access token
     *
     * @param authCallback 授权成功后的回调参数
     * @return token
     * @see AuthDefaultRequest#authorize()
     * @see AuthDefaultRequest#authorize(String)
     */
    protected abstract AuthToken getAccessToken(AuthCallback authCallback);

    /**
     * 使用token换取用户信息
     *
     * @param authToken token信息
     * @return 用户信息
     * @see AuthDefaultRequest#getAccessToken(AuthCallback)
     */
    //protected abstract AuthUser getUserInfo(AuthToken authToken);

    /**
     * 统一的登录入口。当通过{@link AuthDefaultRequest#authorize(String)}授权成功后，会跳转到调用方的相关回调方法中
     * 方法的入参可以使用{@code AuthCallback}，{@code AuthCallback}类中封装好了OAuth2授权回调所需要的参数
     *
     * @param authCallback 用于接收回调参数的实体
     * @return AuthResponse
     */
    @Override
    public Response login(AuthCallback authCallback) {

        if (!config.isIgnoreCheckState()) {
            // 校验 checkState, 防止 csrf 攻击
            checkState(authCallback.getState());
        }
        //AuthChecker.checkCode(source, authCallback);
        //String code = authCallback.getCode();

        AuthToken authToken = this.getAccessToken(authCallback);
        AuthUser user = this.getUserInfo(authToken);
        return Response.ok(user);

        //try {
        //
        //} catch (Exception e) {
        //    SysLoggers.user_log.error("Failed to login with oauth authorization.", e);
        //    return Response.error("Failed to login with oauth authorization. e=" + ExceptionUtil.stacktraceToOneLineString(e));
        //}
    }

    /**
     * 使用token换取用户信息
     *
     * @param authToken token信息
     * @return 用户信息
     * @see AuthDefaultRequest#getAccessToken(AuthCallback)
     */
    protected abstract AuthUser getUserInfo(AuthToken authToken);

}
