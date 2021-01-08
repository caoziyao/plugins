//package com.zel.market.es;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
//
///**
// * 继承CRUD，第一个泛型是实体类类型，第二个泛型是id的类型
// */
//public interface TestDao extends CrudRepository<TestBean, Long> {
//    List<TestBean> findByName(String name);
//
//    List<TestBean> findByNameOrDesc(String text);
//}