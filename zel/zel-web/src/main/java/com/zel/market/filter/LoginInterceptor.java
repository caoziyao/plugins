package com.zel.market.filter;

import com.zel.commonutils.JacksonHelper;
import com.zel.commonutils.RequestUtil;
import com.zel.commonutils.crypto.AESEncrypt;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.dbmanager.entity.User;
import com.zel.market.common.AppContext;
import com.zel.market.common.Constants;
import com.zel.market.common.Env;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.exception.AuthorizationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * 只能对controller请求进行拦截，对其他的一些比如直接访问静态资源的请求则没办法进行拦截处理
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/22
 */
@Component
public class LoginInterceptor implements AsyncHandlerInterceptor {

    @Value("${TOKEN_SALT}")
    private String TOKEN_KEY;

    @Autowired
    private RedisUtils redisUtils;

    private void setContext(HttpServletRequest request) throws Exception {
        AppContext appContext = new AppContext();

        String token = RequestUtil.getCookie(request, Constants.SESSIONID);
        if (StringUtils.isBlank(token)) {
            return;
        }
        String userId = new AESEncrypt(TOKEN_KEY).decrypt(token).split("-")[0];
        String userStr = (String) redisUtils.get(ERedisKey.USER_ID.formatKey(userId));
        if (StringUtils.isBlank(userStr)) {
            return;
        }
        User user = JacksonHelper.read(userStr, User.class);
        appContext.setUser(user);
        Env.setContext(appContext);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        // set content
//        System.out.println("interceptor:" + request.getRequestURI());
//        String curl = RequestUtil.curl(request);
//        System.out.println(curl);

        setContext(request);

        // 校验token
        // 从 session 或 header 获取 token
        String token = RequestUtil.getCookie(request, Constants.SESSIONID);
        if (StringUtils.isBlank(token)) {
            throw new AuthorizationException("请登录");
        }
        String userId = new AESEncrypt(TOKEN_KEY).decrypt(token).split("-")[0];
        // 从 redis 获取用户信息
        String userStr = (String) redisUtils.get(ERedisKey.USER_ID.formatKey(userId));
        if (StringUtils.isBlank(userStr)) {
            throw new AuthorizationException("登录过期");
        }
        User user = JacksonHelper.read(userStr, User.class);
        if (user == null) {
            throw new AuthorizationException("解析 user 出错!");
        }

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
