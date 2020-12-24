package com.zel.market.filter;

import com.zel.commonutils.client.RequestUtils;
import com.zel.market.common.Response;
import com.zel.market.common.enumcom.EResponseCode;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter有如下几个用处。
 * <p>
 * filter -> interceptor -> ControllerAdvice -> aspect -> controller
 */
@Component
public class MyFilter implements Filter {


    //private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
    //        Arrays.asList("/login", "/superlogin", "/logout", "/swagger-ui.html", "/webjars", "/v2/api-docs", "/csrf")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }


    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("filter: " + RequestUtils.getURL(request));
        //执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }

    private void write(EResponseCode code, HttpServletResponse response) throws IOException {
        Response rsp = new Response(code);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(rsp.toString());
    }
}
