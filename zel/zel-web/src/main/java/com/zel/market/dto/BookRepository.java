//package com.zel.market.dto;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/9/26
 */
//@Component
//public interface BookRepository  extends ElasticsearchRepository<Book,Integer> {
//    Page<Book> findByCatory(String catory, Pageable pageable);
//
//    @Query("{ \"bool\":{ \"must\":[ { \"multi_match\": { \"query\": \"?0\", \"type\": \"most_fields\", \"fields\": [ \"title\", \"content\" ] } }, { \"match\": { \"catory\": \"?1\" } } ] } } ")
//    Page<Book> searchByKeyWordsAndCatory(String keyword, String catory, Pageable pageable);
//}
