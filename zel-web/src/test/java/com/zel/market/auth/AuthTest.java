package com.zel.market.auth;

//import com.zel.commonutils.crypto.UuidUtils;
import com.zel.market.BaseTest;
import com.zel.market.common.Response;
import com.zel.market.dto.AuthCallback;
import com.zel.market.dto.AuthConfig;
import com.zel.market.request.AuthGiteeRequest;
import com.zel.market.request.AuthRequest;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/26
 */
public class AuthTest  extends BaseTest {

    @Test
    void index()  {
        // https://gitee.com/oauth/applications/7881
        // 创建授权request


        //码云认证服务器通过回调地址{redirect_uri}将 用户授权码 传递给 应用服务器 或者直接在 Webview 中跳转到携带 用户授权码的回调地址上，
        // Webview 直接获取code即可（{redirect_uri}?code=abc&state=xyz)
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585")
                .clientSecret("ea876309d0f3f4baab9e5f70b937cef75d9c3eb1d87271c1568ffb8a84965a76")
                .redirectUri("http://49.234.12.16:8899/oauth/login")
                .build());
        // 生成授权页面
        // https://gitee.com/oauth/authorize?response_type=code&client_id=84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585&redirect_uri=http://49.234.12.16:8899/oauth/login&state=random_state&scope=user_info
        //String state = UuidUtils.getUUID();
        String url = authRequest.authorize("random_state");
        System.out.println(url);
        // 授权登录后会返回code（auth_code（仅限支付宝））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的参数
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        //
        //authRequest.authorize(state);
        //AuthCallback callback = AuthCallback.builder()
        //        .code("decdc6ebf94b3a9bc49fa64127b22eb925b9e608d354a450c7e8978b75c314f5")
        //        .state("state")
        //        .build();
        //Response response = authRequest.login(callback);
        //System.out.println(response);
    }
}
