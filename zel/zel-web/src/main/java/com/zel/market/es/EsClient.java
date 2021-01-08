package com.zel.market.es;

import com.zel.commonutils.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 静态内部类
 *
 * 关系型数据库（如MySQL）	ElasticSearch
 * 数据库（database）	索引（index）
 * 表（table）	        类型（type）
 * 行（row）	            文档（document）
 * 列（column）	        字段（field）
 * 模式/表结构（schema）	映射（mapping）
 * 索引	                倒排索引
 * INSERT	            PUT
 * DELETE	            DELETE
 * UPDATE	            PUT/POST
 * SELECT	            GET
 *
 */
public class EsClient {

    @Value("${es.url}")
    private String esUrl;
    @Value("${es.username}")
    private String userName;
    @Value("${es.password}")
    private String password;

    private RestHighLevelClient client = null;

    private EsClient(){}

    // 静态方法
    public static EsClient getInstance(){
        return EsClientHolder.INSTANCE ;
    }

    //静态内部类
    private static class EsClientHolder {
        private static final  EsClient  INSTANCE  = new EsClient();
    }

    public RestHighLevelClient getClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http"));

        System.out.println("test");
        client = new RestHighLevelClient(builder);
        return client;
    }

    public synchronized void close() {
        if(null != client) {
            try {
                client.close();
                client = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入文档
     * @param index		索引名
     * @param id		主键ID
     * @param jsonMap	文档
     */
    public String add(String index, String id, Map<String, Object> jsonMap) {
        try {
            if(null == jsonMap || jsonMap.isEmpty()) {
                return null;
            }
            IndexRequest request = new IndexRequest(index);
            if(StringUtils.isNotBlank(id)) {
                request.id(id.trim());
            }
            request.source(jsonMap);

            IndexResponse rs = this.getClient().index(request, RequestOptions.DEFAULT);

            return rs.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入文档
     * @param index	索引名
     * @param id	主机ID
     * @param json	文档
     * @return
     */
    public String add(String index, String id, String json) {
        try {
            if(StringUtils.isBlank(json)) {
                return null;
            }
            IndexRequest request = new IndexRequest(index);
            if(StringUtils.isNotBlank(id)) {
                request.id(id.trim());
            }
            request.source(json, XContentType.JSON);

            IndexResponse rs = this.getClient().index(request, RequestOptions.DEFAULT);
            return rs.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除温度计
     * @param index	索引名
     * @param id	主键ID
     */
    public void delete(String index, String id) {
        try {
            if(StringUtils.isBlank(id)) {
                return;
            }

            DeleteRequest request = new DeleteRequest(index, id);

            DeleteResponse response = this.getClient().delete(request, RequestOptions.DEFAULT);
            System.out.println(response.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新文档
     * @param index	索引名
     * @param id	主键ID
     * @param json	文档
     */
    public void update(String index, String id, String json) {
        try {
            if(StringUtils.isBlank(id) || StringUtils.isBlank(json)) {
                return;
            }
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(json, XContentType.JSON);

            UpdateResponse update = this.getClient().update(request, RequestOptions.DEFAULT);
            System.out.println(update.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新文档
     * @param index		索引名
     * @param id		主键ID
     * @param jsonMap	文档
     */
    public void update(String index, String id, Map<String, Object> jsonMap) {
        try {
            if(StringUtils.isBlank(id)) {
                return;
            }
            if(null == jsonMap || jsonMap.isEmpty()) {
                return;
            }
            UpdateRequest request = new UpdateRequest(index, id);
            request.doc(jsonMap);

            UpdateResponse update = this.getClient().update(request, RequestOptions.DEFAULT);
            System.out.println(update.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询文档
     * @param index		索引名
     * @param id		主键ID
     * @param clazz		反序列化对象
     * @return
     */
    public <T> T get(String index, String id, Class<T> clazz) {
        try {
            GetRequest getRequest = new GetRequest(index, id);
            GetResponse response = this.getClient().get(getRequest, RequestOptions.DEFAULT);

            if(response.isExists()) {
                return JsonHelper.read(JsonHelper.write(response.getSource()), clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
