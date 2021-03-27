package com.zel.market.app.filter;

import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.client.CookieUtil;
import com.zel.commonutils.client.RequestUtil;
import com.zel.commonutils.crypto.AESEncrypt;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.exception.BusinessException;
import com.zel.pojo.entity.User;
import com.zel.market.common.AppContext;
import com.zel.market.common.Constants;
import com.zel.market.common.Env;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.exception.AuthException;
import com.zel.market.app.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * 只能对controller请求进行拦截，对其他的一些比如直接访问静态资源的请求则没办法进行拦截处理
 *
 *  filter -> interceptor -> ControllerAdvice -> aspect -> controller
 */
@Component
public class LoginInterceptor implements AsyncHandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${TOKEN_SALT}")
    private String TOKEN_KEY;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    //private void setContext(HttpServletRequest request) throws Exception {
    //    AppContext appContext = new AppContext();
    //
    //    String token = CookieUtil.getCookie(request, Constants.SESSIONID);
    //    if (StringUtils.isBlank(token)) {
    //        return;
    //    }
    //    String userId =  AESEncrypt.getInstance(TOKEN_KEY).decrypt(token).split("-")[0];
    //    String userStr = (String) redisUtils.get(ERedisKey.USER_ID.formatKey(userId));
    //    if (StringUtils.isBlank(userStr)) {
    //        return;
    //    }
    //    User user = JsonHelper.read(userStr, User.class);
    //    appContext.setUser(user);
    //    Env.setContext(appContext);
    //}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String ip = RequestUtil.getIpAddr(request);

        // 获取token, 从 session 或 header 获取 token
        String token = CookieUtil.getCookie(request, Constants.SESSIONID);

        // 获取会话参数. 从 redis 获取用户信息
        HttpSession session = request.getSession();

        if (StringUtils.isBlank(token)) {
            throw new AuthException("请登录");
        }

        String userId =  AESEncrypt.getInstance(TOKEN_KEY).decrypt(token).split("-")[0];
        String userStr = (String) redisUtils.get(ERedisKey.USER_ID.formatKey(userId));
        if (StringUtils.isBlank(userStr)) {
            throw new AuthException("登录过期");
        }
        User user = JsonHelper.read(userStr, User.class);
        if (user == null) {
            throw new AuthException("解析 user 出错!");
        }

        // 禁用
        if (user.getStatus() == 1) {
            LOG.info("the access is too frequent, userId={}", userId);
            throw new BusinessException(EResponseCode.C40005);
        }

        // 设置在线
        userService.addOnlineUser(user.getId());

        //设置应用上下文
        AppContext context = new AppContext();
        context.setUser(user);
        context.setUserId(user.getId());
        Env.setContext(context);
        LOG.info("validate succeed and init context is ok, userId={}, token={}, ip={}", context.getUserId(), token, ip);

        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        /**
         * 编写拦截器，当请求处理完成后（从Controller返回后），清除ThreadLocal中的id，避免内存泄漏。
         */
        Env.remove();
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
