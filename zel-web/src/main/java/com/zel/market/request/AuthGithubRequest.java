package com.zel.market.request;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */

//import com.sun.deploy.net.HttpUtils;
import com.zel.commonutils.client.GlobalAuthUtils;
import com.zel.commonutils.client.HttpUtil;
import com.zel.commonutils.client.UrlBuilder;
import com.zel.market.common.SysLoggers;
import com.zel.market.dto.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Github登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0.0
 */
public class AuthGithubRequest extends AuthDefaultRequest {

    public AuthGithubRequest(AuthConfig config) {
        super(config, AuthDefaultSource.GITHUB);
    }


    //@Override
    //protected AuthToken getAccessToken(AuthCallback authCallback) {
    //    return null;
    //}

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
                .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthGithubScope.values())))
                .build();
    }


    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String response = doPostAuthorizationCode(authCallback.getCode());
        Map<String, String> res = GlobalAuthUtils.parseStringToMap(response);

        //this.checkResponse(res.containsKey("error"), res.get("error_description"));

        return AuthToken.builder()
                .accessToken(res.get("access_token"))
                .scope(res.get("scope"))
                .tokenType(res.get("token_type"))
                .build();
    }


    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        Map<String, String> header = new HashMap();
        header.put("Authorization", "token " + authToken.getAccessToken());
        String response =  HttpUtil.get(UrlBuilder.fromBaseUrl(source.userInfo()).build(), header);
        //JSONObject object = JSONObject.parseObject(response);

        SysLoggers.user_log.info("github|getUserInfo|{}", response);
        //JSONObject object = new JSONObject(userInfo);
        //this.checkResponse(object);
        return AuthUser.builder().source(response).build();
        //return AuthUser.builder()
        //        .rawUserInfo(object)
        //        .uuid(object.getString("id"))
        //        .username(object.getString("login"))
        //        .avatar(object.getString("avatar_url"))
        //        .blog(object.getString("blog"))
        //        .nickname(object.getString("name"))
        //        .company(object.getString("company"))
        //        .location(object.getString("address"))
        //        .email(object.getString("email"))
        //        .remark(object.getString("bio"))
        //        //.gender(AuthUserGender.UNKNOWN)
        //        .token(authToken)
        //        .source(source.toString())
        //        .build();
    }

}
