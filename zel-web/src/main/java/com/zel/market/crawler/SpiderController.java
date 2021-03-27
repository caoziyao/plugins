package com.zel.market.crawler;

import com.zel.market.common.Response;
import com.zel.market.crawler.pageprocessor.DoubanFilmProcessor;
import com.zel.market.crawler.pageprocessor.ResultItems;
import com.zel.market.crawler.pipeline.Pipeline;
import com.zel.market.crawler.scheduler.QueueScheduler;
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

        Spider spider = new Spider("http://www.baidu.com", new DoubanFilmProcessor())
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems) {
                        System.out.println("resp " + resultItems);
                    }
                });
        spider.start();
        // 运行任务
        //spiderEngineProcess.takeThread();
        //spiderEngineProcess.submitNewsJob();
        return Response.ok();
    }
}
