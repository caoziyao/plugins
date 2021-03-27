package com.zel.market.app.filter;

//import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        RateLimiterUtils.trySingleAcquire(200L, TimeUnit.MILLISECONDS);
        this.validate(req);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    protected abstract void validate(HttpServletRequest request);
}
