package com.zel.market.request.gitee;

import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.client.UrlBuilder;
import com.zel.market.common.SysLoggers;
import com.zel.market.dto.*;
import com.zel.market.request.AuthDefaultRequest;
import com.zel.market.request.AuthScopeUtils;
import com.zel.market.request.AuthStateCache;
import org.json.JSONObject;

/**
 * Gitee登录
 *
 */
public class AuthGiteeRequest extends AuthDefaultRequest {

    public AuthGiteeRequest(AuthConfig config) {
        super(config, AuthDefaultSource.GITEE);
    }

    public AuthGiteeRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.GITEE, authStateCache);
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
                .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthGiteeScope.values())))
                .build();
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = new JSONObject(response);
        //this.checkResponse(accessTokenObject);
        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .refreshToken(accessTokenObject.getString("refresh_token"))
                .scope(accessTokenObject.getString("scope"))
                .tokenType(accessTokenObject.getString("token_type"))
                .expireIn(accessTokenObject.getInt("expires_in"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String userInfo = doGetUserInfo(authToken);

        SysLoggers.user_log.info("github|getUserInfo|{}", JsonHelper.write(authToken));
        AuthGiteeUser user = JsonHelper.read(userInfo, AuthGiteeUser.class);
        //JSONObject object = new JSONObject(userInfo);
        //this.checkResponse(object);
        //return AuthUser.builder().source(userInfo).build();
        return AuthUser.builder()
                .rawUserInfo(user)
                .uuid(String.valueOf(user.getId()))
                .username(user.getLogin())
                .avatar(user.getAvatarUrl())
                .blog(user.getBlog())
                .nickname(user.getName())
                .company(null)
                .location(null)
                .email(user.getEmail())
                .remark(user.getBio())
                //.gender(AuthUserGender.UNKNOWN)
                .token(authToken)
                .source(source.toString())
                .build();
    }
}
