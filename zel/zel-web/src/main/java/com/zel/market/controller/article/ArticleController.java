package com.zel.market.controller.article;

import com.zel.dbmanager.entity.Article;
import com.zel.market.common.Response;
import com.zel.market.controller.article.dto.ArticleReqBody;
import com.zel.market.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 收藏文章
     * @param id 文章id
     * @return
     */
    @GetMapping(value = "/collect")
    public Response collect(@RequestParam("id") long id) {

        return Response.ok(id);
    }
}