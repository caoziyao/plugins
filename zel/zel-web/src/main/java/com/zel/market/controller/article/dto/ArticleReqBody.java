package com.zel.market.controller.article.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
@Getter
@Setter
public class ArticleReqBody {

    private long articleId;

    private long columnId;
}
