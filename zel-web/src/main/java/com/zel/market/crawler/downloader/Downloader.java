package com.zel.market.crawler.downloader;

import com.zel.market.crawler.CrawRequest;
import com.zel.market.crawler.pageprocessor.Page;

/**
 * Description:
 * 负责从互联网上下载页面，以便后续处理
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public interface Downloader {

    /**
     * Downloads web pages and store in Page object.
     *
     * , Task task
     */
    Page download(CrawRequest request);
}
