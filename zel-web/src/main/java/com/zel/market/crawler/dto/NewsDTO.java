package com.zel.market.crawler.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@Getter
@Setter
public class NewsDTO {
    // 新闻标题
    private String title;
    //描述
    private String desc;
    //哪一天的新闻
    private String belongDate;
    //发布结构
    private String announcer;
    //初步分类（0 其它新闻、1科技新闻、2、融资收购）
    private int category;
    //链接
    private String links;
}
