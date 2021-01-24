//package com.zel.market.client;
//
//import com.zel.market.BaseTest;
//import com.zel.market.dto.Book;
//import com.zel.market.dto.BookRepository;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Description:
// *
// * @author csy
// * @version 1.0.0
// * @since 2020/12/26
// */
//public class ESTest extends BaseTest {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    //@Autowired
//    //private RestHighLevelClient restHighLevelClient;
//
//    @Test
//    public void index() {
//        //Book book=new Book();
//        //book.setId(13);
//        //book.setBookName("abdljlk");
//        //bookRepository.save(book);
//
//        //IndexRequest request = new IndexRequest("posts");
//        //request.id("1");
//        //String jsonString = "{" +
//        //        "\"user\":\"kimchy\"," +
//        //        "\"postDate\":\"2013-01-30\"," +
//        //        "\"message\":\"trying out Elasticsearch\"" +
//        //        "}";
//        //request.source(jsonString, XContentType.JSON);
//
//        //// 通过索引、类型、id向es进行查询数据
//        //GetResponse response = restHighLevelClient.prepareGet(index, type, id).get();
//        //
//        //// 通过索引、类型、id向es进行查询数据
//        //GetResponse response = restHighLevelClient.prepareGet(index, type, id).get();
//    }
//}
