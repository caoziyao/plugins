package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.pojo.entity.Book;
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
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT * FROM book")
    List<Book> findAll();

}
