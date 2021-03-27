package com.zel.market.crawler.pipeline;

import com.zel.market.crawler.pageprocessor.ResultItems;

import java.util.Map;

/**
 * Description: 输出到页面
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class ConsolePipeline implements Pipeline{
    @Override
    public void process(ResultItems resultItems) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getFields().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
