package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from tb_article")
    List<Article> findAll();
}
