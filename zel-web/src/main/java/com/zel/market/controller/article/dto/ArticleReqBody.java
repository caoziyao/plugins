package com.zel.market.controller.article.dto;

import com.zel.market.controller.dto.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/5/20
 */
@Getter
@Setter
public class ArticleReqBody extends BaseReqDTO {

    private long articleId;
    private long columnId;

    private boolean refresh;
}
