package com.zel.market.filter;

import com.zel.commonutils.IpUtil;
import com.zel.commonutils.client.CookieUtil;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Filter有如下几个用处。
 * <p>
 * filter -> interceptor -> ControllerAdvice -> aspect -> controller
 */
@Component
public class FirstFilter extends AbstractFilter {

    private static final Logger LOG = LoggerFactory.getLogger(FirstFilter.class);

    /**
     * 1分钟内一个ip只能访问200次
     */
    private final RateLimiterUtils rateLimiterUtils = RateLimiterUtils.build("OpenApiV1Filter", 1L, TimeUnit.MINUTES, 200L);

    protected void validate(HttpServletRequest request) {
        LOG.info("filter: {}", CookieUtil.getURL(request));

        //物流查询接口限制
        String ip = IpUtil.getIpAddr(request);
        if ("/v1/open/api/routeQuery".equals(request.getRequestURI())) {
            if (!rateLimiterUtils.tryAcquire(ip)) {
                throw new BusinessException(EResponseCode.C40005);
            }
        }
    }

    //private void write(EResponseCode code, HttpServletResponse response) throws IOException {
    //    Response rsp = new Response(code);
    //    response.setHeader("Content-Type", "application/json;charset=UTF-8");
    //    response.getWriter().write(rsp.toString());
    //}
}
