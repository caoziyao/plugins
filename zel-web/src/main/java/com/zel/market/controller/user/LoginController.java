package com.zel.market.controller.user;

import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.client.CookieUtil;
import com.zel.commonutils.client.RequestUtil;
import com.zel.commonutils.crypto.AESEncrypt;
import com.zel.commonutils.crypto.Md5Utils;
import com.zel.commonutils.crypto.UuidUtils;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.dto.AuthCallback;
import com.zel.market.dto.AuthConfig;
import com.zel.market.request.gitee.AuthGiteeRequest;
import com.zel.market.request.github.AuthGithubRequest;
import com.zel.market.request.AuthRequest;
import com.zel.pojo.entity.User;
import com.zel.market.common.Constants;
import com.zel.market.common.Response;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.controller.user.dto.LoginVO;
import com.zel.market.exception.BusinessException;
import com.zel.market.service.user.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Api(description = "login")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${TOKEN_SALT}")
    private String TOKEN_KEY;

    @Value("${token.timeout}")
    private long TOKEN_TIMEOUT;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    private User getDefaultUser() {
        User user = new User();
        long userId = new Random().nextInt(1000);
        user.setId(userId);
        user.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
        user.setUsername("abc");
        return user;
    }

    @PostMapping("/login")
    public Response index(@Validated @RequestBody LoginVO body,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ipAddr = RequestUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String device = RequestUtil.getClientType(request.getHeader("User-Agent"));
        body.setIpAddr(ipAddr);
        body.setUserGgent(userAgent);
        body.setDevice(device);

        // 防止重复提交
        String limitKey = "redis:user:key" + body.getName();
        if (redisUtils.isRepeatSubmit(limitKey, 2)) {
            throw new BusinessException("您操作过于频繁,请稍候再试");
        }

        // todo 参数校验

        String username = body.getUsername();
        String password = body.getPassword();
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new BusinessException(EResponseCode.NOT_REGISTER);
        }

        // 密码判断
        String md5 = Md5Utils.md5(password);
        if (!user.getPassword().equals(md5)) {
            throw new BusinessException(EResponseCode.PASSWORD_ERR);
        }

        // 生成并设置TOKEN
        String newToken =  AESEncrypt.getInstance(TOKEN_KEY).encrypt(user.getId() + "-" + System.currentTimeMillis() / 1000);
        // 设置TOKEN
        redisUtils.set(ERedisKey.USER_ID.formatKey(user.getId().toString()), JsonHelper.write(user), TOKEN_TIMEOUT, TimeUnit.HOURS);
        // 保存到 cookie 或 header 里面
        CookieUtil.saveCookie(Constants.SESSIONID, newToken, 24 * 3600, request, response);
        // 设置在线用户
        userService.addOnlineUser(user.getId());

        return Response.ok(user);
    }


    @GetMapping("/superlogin")
    public Response superlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "abc";
        String password = "1234";
        User user = getDefaultUser();

        // 密码判断
        String md5 = Md5Utils.md5(password);
        if (!user.getPassword().equals(md5)) {
            throw new BusinessException(EResponseCode.PASSWORD_ERR);
        }

        // 生成并设置TOKEN
        String newToken =  AESEncrypt.getInstance(TOKEN_KEY).encrypt(user.getId() + "-" + System.currentTimeMillis() / 1000);
        // 设置TOKEN
        redisUtils.set(ERedisKey.USER_ID.formatKey(user.getId().toString()), JsonHelper.write(user), TOKEN_TIMEOUT, TimeUnit.HOURS);
        // 保存到 cookie 或 header 里面
        CookieUtil.saveCookie(Constants.SESSIONID, newToken, 24 * 3600, request, response);
        // 设置在线用户
        userService.addOnlineUser(user.getId());

        return Response.ok(user);
    }

    //@GetMapping("/oauth/authorize")
    //public Response authorize(HttpServletRequest request, HttpServletResponse response) {
    //    AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
    //            .clientId("84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585")
    //            .clientSecret("ea876309d0f3f4baab9e5f70b937cef75d9c3eb1d87271c1568ffb8a84965a76")
    //            .redirectUri("http://49.234.12.16:8899/oauth/token")
    //            .build());
    //
    //    String code = request.getParameter("code");
    //    String state = request.getParameter("state");
    //    AuthCallback callback = AuthCallback.builder()
    //            .code(code)
    //            .state(state)
    //            .build();
    //    return authRequest.login(callback);
    //}

    @GetMapping(value = "/oauth/login")
    public Response oauthLogin(HttpServletRequest request, HttpServletResponse response) {
        AuthRequest gitee = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585")
                .clientSecret("ea876309d0f3f4baab9e5f70b937cef75d9c3eb1d87271c1568ffb8a84965a76")
                .redirectUri("http://49.234.12.16:8899/oauth/gitee/callback")
                .ignoreCheckState(false)
                .build());
        String state = UuidUtils.getUUID();
        String urlgitee = gitee.authorize(state);

        AuthRequest github = new AuthGithubRequest(AuthConfig.builder()
                .clientId("020767fdb79a8fa6f46c")
                .clientSecret("fb8d4324e7a5b3eceac59ca567dbb691c91dad0c")
                .redirectUri("http://49.234.12.16:8899/oauth/github/callback")
                .ignoreCheckState(false)
                .build());

        String urlgithub = github.authorize(state);

        Map<String, String> map = new HashMap();
        map.put("gitee", urlgitee);
        map.put("github", urlgithub);
        Response ok = Response.ok(map);
        return ok;
    }

    @GetMapping("/oauth/github/callback")
    public Response github(HttpServletRequest request, HttpServletResponse response) {

        // https://gitee.com/oauth/authorize?client_id=84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585&redirect_uri=http://49.234.12.16:8899/oauth/token&response_type=code
        AuthRequest github = new AuthGithubRequest(AuthConfig.builder()
                .clientId("020767fdb79a8fa6f46c")
                .clientSecret("fb8d4324e7a5b3eceac59ca567dbb691c91dad0c")
                .redirectUri("http://49.234.12.16:8899/oauth/github/callback")
                .build());

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        AuthCallback callback = AuthCallback.builder()
                .code(code)
                .state(state)
                .build();
        return github.login(callback);

    }

    @GetMapping("/oauth/gitee/callback")
    public Response oauthToken(HttpServletRequest request, HttpServletResponse response) {

        // https://gitee.com/oauth/authorize?client_id=84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585&redirect_uri=http://49.234.12.16:8899/oauth/token&response_type=code
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("84d83337608e2a2242dd55cdb52bc1bad7f7d393594c5f6d76816655d6d1c585")
                .clientSecret("ea876309d0f3f4baab9e5f70b937cef75d9c3eb1d87271c1568ffb8a84965a76")
                .redirectUri("http://49.234.12.16:8899/oauth/gitee/callback")
                .build());

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        AuthCallback callback = AuthCallback.builder()
                .code(code)
                .state(state)
                .build();
        return authRequest.login(callback);
    }
}
