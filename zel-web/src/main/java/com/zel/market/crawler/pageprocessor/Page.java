package com.zel.market.crawler.pageprocessor;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
@Setter
@Getter
public class Page {

    private String data;

    private String filePath;

    private ResultItems resultItems;
}
