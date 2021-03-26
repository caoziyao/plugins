package com.zel.market.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 * Spring 5.0后，WebMvcConfigurerAdapter被废弃，取代的方法有两种：
 *
 * ①implements WebMvcConfigurer（官方推荐） 或者 extends WebMvcConfigurerAdapter
 *
 * ②extends WebMvcConfigurationSupport
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor httpInterceptor;

    //private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
    //        Arrays.asList("/login", "/superlogin", "/logout", "/swagger-ui.html", "/webjars", "/v2/api-docs", "/csrf")));

    /**
     * addPathPatterns 添加拦截规则
     * excludePathPatterns 排除拦截规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(httpInterceptor).addPathPatterns("/*");
        registry.addInterceptor(httpInterceptor).addPathPatterns("/*/*");
        registry.addInterceptor(httpInterceptor).excludePathPatterns("/login", "/superlogin", "/oauth/*");
    }
}
