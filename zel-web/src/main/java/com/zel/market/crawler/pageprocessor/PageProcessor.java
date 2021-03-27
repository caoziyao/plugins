package com.zel.market.crawler.pageprocessor;

/**
 * Description: PageProcessor负责解析页面，抽取有用信息，以及发现新的链接。
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public interface PageProcessor {
    /**
     * process the page, extract urls to fetch, extract the data and store
     */
    public void process(Page page);
}
