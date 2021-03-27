package com.zel.market.crawler;

import com.zel.market.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@RestController
@RequestMapping(value = "/spider")
public class SpiderController {

    @Autowired
    private SpiderEngineProcess spiderEngineProcess;


    @GetMapping("/run")
    public Response spider() {
        // 运行任务
        spiderEngineProcess.takeThread();
        spiderEngineProcess.submitNewsJob();
        return Response.ok();
    }
}
