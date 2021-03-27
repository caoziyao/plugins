package com.zel.market.crawler.pipeline;

import com.zel.market.crawler.pageprocessor.ResultItems;

/**
 * Description: Pipeline负责抽取结果的处理，包括计算、持久化到文件、数据库等。
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public interface Pipeline {
    /**
     * Process extracted results.

     */
    void process(ResultItems resultItems);
}
