package com.zel.dbmanager;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 乐观锁原理
 * 乐观锁：故名思意十分乐观，总是认为不会出现问题，无论干什么不去上锁，先进行事务，如果出现了问题，再次更新值测试
 * 悲观锁：故名思意十分悲观，总是认为总是出现问题，无论干什么都会上锁，再去操作
 *
 * 乐观锁实现方式：
 *
 * 取出记录时，获取当前versionl
 * 更新时，带上这个version执行
 * 更新时，set version = newVersion where version =oldVersion
 * 如果version不对，就更新失败
 *
 * 举例：
 *
 * 先查询出 version，进行操作时 version + 1
 * 线程A：
 * update user set name = "zc",version = version+1 where id=? and version=1
 *
 * 线程B：
 * update user set name = "zc",version = version+1 where id=? and version=1
 * 可以看出，先查询了老的version，在更新时version+1；
 *
 * 如果 线程B先于线程A完成该更新操作，那version==2，这时候线程A不成立，更新失败
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.zel.dbmanager.mapper")
public class MyBatisPlusConfig {
    //注册乐观锁插件

    // 注册乐观锁和分页插件(新版：3.4.0)
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁插件

        // DbType：数据库类型(根据类型获取应使用的分页方言)
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页插件
        return interceptor;
    }
}
