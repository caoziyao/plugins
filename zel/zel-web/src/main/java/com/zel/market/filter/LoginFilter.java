package com.zel.market.filter;

import com.zel.commonutils.JacksonHelper;
import com.zel.commonutils.RequestUtil;
import com.zel.commonutils.crypto.AESEncrypt;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.dbmanager.entity.User;
import com.zel.market.common.AppContext;
import com.zel.market.common.Constants;
import com.zel.market.common.Env;
import com.zel.market.common.Response;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.exception.AuthorizationException;
import com.zel.market.utils.Loggers;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class LoginFilter implements Filter {

    @Autowired
    private RedisUtils redisUtils;

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "/logout", "/swagger-ui.html", "/webjars", "/v2/api-docs", "/csrf")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        Loggers.interceptor_log.info("TestFilter: "+ request.getRequestURI());

//        AppContext appContext = new AppContext();

//        String token2 = req.getHeader("token");
//
//        appContext.setUserId(123);
//        appContext.setToken(token2);
//        Env.setContext(appContext);

        String path = request.getRequestURI();
        if (ALLOWED_PATHS.contains(path) || path.contains("swagger")) {
            // 登录接口
        } else {
            // 从 session 或 header 获取 token
            String token = RequestUtil.getCookie(request, Constants.SESSIONID);
            if (StringUtils.isBlank(token)) {
                // throw new AuthorizationException("请登录");
                writeResponse(EResponseCode.C403, response);
                return;
            }
            String userId = new AESEncrypt("random key").decrypt(token).split("-")[0];
            // 从 redis 获取用户信息
            String userStr = (String)redisUtils.get(ERedisKey.USERID.formatKey(userId));
            User user = JacksonHelper.read(userStr, User.class);
            if (user == null) {
                writeResponse(EResponseCode.C405, response);
                return;
                //throw new AuthorizationException("登录过期");
            }
            // 校验token
            //if (StringUtils.isEmpty(token)) {
            //    //throw new AuthorizationException("token is null");
            //    Response rsp = new Response(EResponseCode.C403);
            //    response.setHeader("Content-Type", "application/json;charset=UTF-8");
            //    response.getWriter().write(rsp.toString());
            //    Loggers.interceptor_log.error("拒绝：" + path);
            //    return;
            //}
        }

        //执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void writeResponse(EResponseCode code, HttpServletResponse response) throws IOException {
        Response rsp = new Response(code);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(rsp.toString());
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
