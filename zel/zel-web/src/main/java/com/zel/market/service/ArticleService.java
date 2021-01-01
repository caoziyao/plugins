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

    /**
     * 文章
     * @param page
     * @param limit
     * @return
     */
    public List<Article> getArticle(int page, int limit) {
        Map<String, Object> params = new HashMap<>();
        int offset = limit * page - limit;
        params.put("offset", offset);
        params.put("limit", limit);
        List<Article> all = articleMapper.findAll(params);
        return all;
    }

    /**
     * 总数
     * @return
     */
    public int totalArticle() {
        return articleMapper.countAll();
    }

    /**
     * 新增 文章
     * @return
     */
    public Article add(Article article) {
        articleMapper.insert(article);
        return article;
    }
}
