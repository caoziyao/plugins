package com.zel.dbmanager.service;

import com.zel.dbmanager.DBManager;
import com.zel.dbmanager.entity.Book;
import com.zel.dbmanager.mapper.BookMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/11
 */
public class BookService extends DBManager {

    private static BookMapper bookMapper = null;

    static {
        SqlSession session = sqlSessionFactory.openSession();
        bookMapper = session.getMapper(BookMapper.class);
    }

    public List<Book> findAll() {
        List<Book> all = bookMapper.findAll();
        return Optional.ofNullable(all).orElse(new ArrayList<>());
    }
}
