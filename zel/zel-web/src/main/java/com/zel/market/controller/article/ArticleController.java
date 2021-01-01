package com.zel.market.controller.article;

import com.zel.pojo.entity.Article;
import com.zel.market.common.Response;
import com.zel.market.controller.article.dto.ArticleReqBody;
import com.zel.market.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    private final static Logger log = LogManager.getLogger(ArticleController.class);


    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表
     * @param body
     * @return
     */
    @PostMapping(value = "/list")
    public Response list(@RequestBody ArticleReqBody body) {
        long articleId = body.getArticleId();
        long columnId = body.getColumnId();
        List<Article> allArticle = articleService.getAllArticle(articleId, columnId);

        return Response.ok(allArticle);
    }

    /**
     * 添加列表
     * @param body
     * @return
     */
    @PostMapping(value = "/add")
    public Response add(@RequestBody Map<String,String> map) {
        String title = map.getOrDefault("title", "");
        String url = map.getOrDefault("url", "");

        Article article = new Article();
        article.setTitle(title);
        article.setArticleUrl(url);
        articleService.add(article);

        return Response.ok(article);
    }

    /**
     * 收藏文章
     * @param id 文章id
     * @return
     */
    @GetMapping(value = "/collect")
    public Response collect(@RequestParam("id") long id) {
        return Response.ok(id);
    }
}