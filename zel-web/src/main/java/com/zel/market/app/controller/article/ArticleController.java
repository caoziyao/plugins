package com.zel.market.app.controller.article;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.app.controller.article.dto.ArticleListVO;
import com.zel.pojo.entity.Article;
import com.zel.market.common.Response;
import com.zel.market.app.controller.article.dto.ArticleReqBody;
import com.zel.market.app.service.article.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 文章列表
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/list")
    public Response list(@RequestBody ArticleReqBody body) {
        int page = body.getPage();
        int limit = body.getLimit();
        boolean refresh = body.isRefresh();
        List<Article> articles = new ArrayList<>();

        String key = ERedisKey.ARTICLE_PAGE.formatKey(page, limit);
        if (refresh) {
            // 读取数据库
            articles = articleService.getArticle(page, limit);
            redisUtils.set(key, JsonHelper.write(articles), 10L, TimeUnit.MINUTES);
        } else {
            String cache = (String) redisUtils.get(key);
            if (StringUtils.isBlank(cache)) {
                articles = articleService.getArticle(page, limit);
                redisUtils.set(key, JsonHelper.write(articles), 10L, TimeUnit.MINUTES);
            } else {
                log.info("命中缓存 {}", key);
                articles = JsonHelper.read(cache, new TypeReference<List<Article>>() {});
            }
        }

        int total = articleService.totalArticle();
        int num = articles.size();
        ArticleListVO vo = ArticleListVO.builder()
                .article(articles)
                .total(total)
                .num(num)
                .build();
        return Response.ok(vo);
    }

    /**
     * 添加列表
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/add")
    public Response add(@RequestBody Map<String, String> map) {
        String title = map.getOrDefault("title", "");
        String url = map.getOrDefault("url", "");
        String column = map.getOrDefault("column", "6");

        if (StringUtils.isBlank(url) || StringUtils.isBlank(title)) {
            return Response.error("title 或者 url 为空");
        }

        Article article = new Article();
        article.setTitle(title);
        article.setArticleUrl(url);
        article.setColumnsId(Integer.parseInt(column));
        articleService.add(article);

        return Response.ok(article);
    }

    /**
     * 收藏文章
     *
     * @param id 文章id
     * @return
     */
    @GetMapping(value = "/collect")
    public Response collect(@RequestParam("id") long id) {
        return Response.ok(id);
    }
}