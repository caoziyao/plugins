package com.zel.dbmanager.service;

import com.zel.dbmanager.DBManager;
import com.zel.dbmanager.entity.Book;
import com.zel.dbmanager.entity.Jobs;
import com.zel.dbmanager.mapper.BookMapper;
import com.zel.dbmanager.mapper.JobsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
//public class JobsService extends DBManager {
//
//    private static JobsMapper jobsMapper = null;
//
//    static {
//        SqlSession session = sqlSessionFactory.openSession();
//        jobsMapper = session.getMapper(JobsMapper.class);
//    }
//
//    public Jobs findAll() {
//        Jobs all = jobsMapper.selectById(1);
//        return all;
//    }
//}
