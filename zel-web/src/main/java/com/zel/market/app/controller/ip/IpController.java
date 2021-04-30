package com.zel.market.app.controller.ip;


import com.zel.commonutils.client.RequestUtil;
import com.zel.market.app.aspect.IpLimiter;
import com.zel.market.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api/ip")
public class IpController {

    /**
     * 这里注解了 IpLimiter，会被 AspectIpLimter.around 环绕执行
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/get")
    @IpLimiter
    public Response get(HttpServletRequest request, HttpServletResponse response) {
        String ip = RequestUtil.getIpAddr(request);

        String msg = "请求成功：" + ip;
        return Response.ok(msg);
    }
}
