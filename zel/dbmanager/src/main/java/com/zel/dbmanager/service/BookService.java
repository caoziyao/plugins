package com.zel.dbmanager.service;

import com.zel.dbmanager.DBManager;
import com.zel.dbmanager.entity.Book;
import com.zel.dbmanager.entity.User;
import com.zel.dbmanager.mapper.BookMapper;
import com.zel.dbmanager.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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

    public static void findAll() {
        List<Book> all = bookMapper.findAll();
        for (Book user: all) {
            System.out.println(user.toString());
        }
    }
}
