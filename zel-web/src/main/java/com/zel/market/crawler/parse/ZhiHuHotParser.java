package com.zel.market.crawler.parse;

import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@Component
public class ZhiHuHotParser {

    private String url;

    public ZhiHuHotParser() {
        //this.url = url;
    }

    public void process() {
        try {
            Thread.sleep(1000);
            System.out.println("zhihu task!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
