package com.zel.market.crawler.downloader;

import com.zel.commonutils.FileUtils;
import com.zel.commonutils.client.HttpUtil;
import com.zel.market.crawler.CrawRequest;
import com.zel.market.crawler.pageprocessor.Page;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class HttpClientDownloader extends AbstractDownloader {

    /**
     * 单例
     * @param site
     * @return
     */
    //private CloseableHttpClient getHttpClient(Site site) {
    //    if (site == null) {
    //        return httpClientGenerator.getClient(null);
    //    }
    //    String domain = site.getDomain();
    //    CloseableHttpClient httpClient = httpClients.get(domain);
    //    if (httpClient == null) {
    //        synchronized (this) {
    //            httpClient = httpClients.get(domain);
    //            if (httpClient == null) {
    //                httpClient = httpClientGenerator.getClient(site);
    //                httpClients.put(domain, httpClient);
    //            }
    //        }
    //    }
    //    return httpClient;
    //}

    @Override
    public Page download(CrawRequest request) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //CloseableHttpResponse httpResponse = httpClient.execute(request.getUrl());
        String s = HttpUtil.get(request.getUrl());

        String url = request.getUrl();

        String filename = url.substring(url.lastIndexOf("/")) + ".html";
        try {
             FileUtils.write("./cache/crawler/" + filename, s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Page page = new Page();
        page.setData(s);

        page.setFilePath(filename);
        return page;
    }
}
