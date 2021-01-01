package com.zel.market.controller.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zel.pojo.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/2
 */
@Getter
@Setter
@Builder
public class ArticleListVO{

    @JsonProperty("article")
    private List<Article> article;

    @JsonProperty("total")
    private int total;

    @JsonProperty("num")
    private int num;
}
