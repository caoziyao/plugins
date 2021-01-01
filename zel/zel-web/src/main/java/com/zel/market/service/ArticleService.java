package com.zel.market.service;

import com.zel.pojo.entity.Article;
import com.zel.dbmanager.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> getAllArticle(long id, long columnId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("column_id", columnId);
        //List<Article> articles = articleMapper.selectByMap(params);
        List<Article> all = articleMapper.findAll();
        return all;
    }

    /**
     * 新增 文章
     * @return
     */
    public Article add(Article article) {
        int insert = articleMapper.insert(article);
        return article;
    }
}
