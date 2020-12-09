package com.zel.market.client;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class HttpConnectionManager {
    public HttpConnectionManager() {
    }

    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }
}
