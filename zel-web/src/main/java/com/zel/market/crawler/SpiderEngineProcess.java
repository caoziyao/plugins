package com.zel.market.crawler;

import com.zel.market.crawler.dto.HotSiteEnum;
import com.zel.market.crawler.parse.DoubanFilmSpider;
import com.zel.market.crawler.parse.NewsServiceSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@Component
public class SpiderEngineProcess {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private NewsServiceSpider newsService;

    @Autowired
    private DoubanFilmSpider zhiHuHotParser;

    public Thread threadNewService() {
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        newsService.takeProcess();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        takeThread.setName("TAKE");
        return takeThread;
    }

    public Thread threadZhiHu() {
        String[] urls = {HotSiteEnum.ZHIHU_HOT.TOPIC_URL, HotSiteEnum.ZHIHU_SPORT.TOPIC_URL, HotSiteEnum.ZHIHU_CAR.TOPIC_URL};
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    zhiHuHotParser.process();
                }
            }
        });
        takeThread.setName("TAKE");
        return takeThread;
    }

    public void submitNewsJob() {
        //newsService.delBeforeData();
        taskExecutor.submit(threadZhiHu());
        //taskExecutor.submit(SpiderEngineProcess.threadIyiou(newsService));
        //taskExecutor.submit(SpiderEngineProcess.threadJieMian(newsService));
    }

    /**
     * 每秒运行任务
     */
    public void takeThread() {
        taskExecutor.submit(threadNewService());
    }
}
