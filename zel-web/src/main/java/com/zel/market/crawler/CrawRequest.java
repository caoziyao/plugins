package com.zel.market.crawler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
@Getter
@Setter
@Builder
public class CrawRequest {
    private String url;

    private String method;

    private String requestBody;
}
