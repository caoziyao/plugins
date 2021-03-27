package com.zel.market.crawler.pageprocessor;

import com.zel.market.crawler.CrawRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 处理页面的结构
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
@Getter
@Setter
public class ResultItems {
    private Map<String, Object> fields = new LinkedHashMap<String, Object>();

    private CrawRequest request;

    private String data;

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object o = fields.get(key);
        if (o == null) {
            return null;
        }
        return (T) fields.get(key);
    }

    public <T> ResultItems put(String key, T value) {
        fields.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "ResultItems{" +
                "fields=" + fields +
                ", request=" + request +
                '}';
    }
}
