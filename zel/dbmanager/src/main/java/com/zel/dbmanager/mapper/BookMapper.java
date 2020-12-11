package com.zel.dbmanager.mapper;

import com.zel.dbmanager.entity.Book;
import com.zel.dbmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/11
 */
@Mapper
public interface BookMapper {
    @Select("SELECT * FROM book")
    List<Book> findAll();

}
