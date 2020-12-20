package com.zel.market.filter;

import com.zel.market.common.AppContext;
import com.zel.market.common.Env;
import com.zel.market.common.Response;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.exception.AuthorizationException;
import com.zel.market.utils.Loggers;
import org.apache.commons.lang3.StringUtils;
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

@Component
public class LoginFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "/logout", "/swagger-ui.html", "/webjars", "/v2/api-docs", "/csrf")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Loggers.interceptor_log.info("TestFilter: "+ request.getRequestURI());

        AppContext appContext = new AppContext();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");

        appContext.setUserId(123);
        appContext.setToken(token);
        Env.setContext(appContext);

        String path = req.getRequestURI();
        if (ALLOWED_PATHS.contains(path) || path.contains("swagger")) {
            // 登录接口
        } else {
            // 校验token
            if (StringUtils.isEmpty(token)) {
                //throw new AuthorizationException("token is null");
                Response rsp = new Response(EResponseCode.C403);
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(rsp.toString());
                Loggers.interceptor_log.error("拒绝：" + path);
                return;
            }
        }

        //执行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
