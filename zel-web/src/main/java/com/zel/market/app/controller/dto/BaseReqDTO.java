package com.zel.market.app.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/2
 */
@Getter
@Setter
public class BaseReqDTO {

    // 默认每页 10 条
    protected int limit = 10;

    protected int page = 1;
}
