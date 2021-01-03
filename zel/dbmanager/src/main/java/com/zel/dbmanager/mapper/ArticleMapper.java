package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from tb_article ORDER BY create_time desc  LIMIT #{offset}, #{limit} ")
    List<Article> findAll(Map<String, Object> params);

    @Select("select count(*) num from tb_article")
    int countAll();
}
