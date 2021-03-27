package com.zel.market.crawler.dto;


/**
 * 热榜源站
 */
public enum HotSiteEnum {
    BAIDU(1, 1, "百度", "实时热点", "https://www.baidu.com", "http://top.baidu.com/buzz?b=1&c=513&fr=topbuzz_b341_c513"),
    ZHIHU_DAILY(2, 2, "知乎", "知乎日报", "https://daily.zhihu.com"),
    ZHIHU_HOT(3, 2, "知乎", "知乎热榜", "https://www.zhihu.com", "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&desktop=tru"),
    ZHIHU_SPORT(4, 2, "知乎", "体育榜", "https://www.zhihu.com", "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/sport?limit=50&desktop=true"),
    ZHIHU_CAR(5, 2, "知乎", "汽车榜", "https://www.zhihu.com", "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/car?limit=50&desktop=true"),
    WEIBO(6, 3, "微博", "热搜榜", "https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6"),
    DOUYIN_WORD(7, 4, "抖音", "热点榜", "https://www.iesdouyin.com/share/billboard", "https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/word/"),
    DOUYIN_VIDEO(8, 4, "抖音", "视频榜", "https://www.iesdouyin.com/share/billboard", "https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/aweme/"),
    JI_KI_PE_DIA(9, 5, "小鸡词典", "热门搜索", "https://jikipedia.com/", "https://api.jikipedia.com/go/get_hot_search"),
    BILI_BILI(10, 6, "哔哩哔哩", "全站日榜", "https://www.bilibili.com/", "https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all"),
    ;

    public Integer ID;
    public Integer SITE_ID;
    public String SITE_NAME;
    public String TOPIC;
    public String ORG_URL;
    public String TOPIC_URL;

    HotSiteEnum(Integer ID, Integer SITE_ID, String SITE_NAME, String TOPIC, String ORG_URL, String TOPIC_URL) {
        this.ID = ID;
        this.SITE_ID = SITE_ID;
        this.SITE_NAME = SITE_NAME;
        this.TOPIC = TOPIC;
        this.ORG_URL = ORG_URL;
        this.TOPIC_URL = TOPIC_URL;
    }

    HotSiteEnum(Integer ID, Integer SITE_ID, String SITE_NAME, String TOPIC, String ORG_URL) {
        this.ID = ID;
        this.SITE_ID = SITE_ID;
        this.SITE_NAME = SITE_NAME;
        this.TOPIC = TOPIC;
        this.ORG_URL = ORG_URL;
    }

    public static HotSiteEnum valOf(Integer id) {
        for (HotSiteEnum item : HotSiteEnum.values()) {
            if (item.ID == id) {
                return item;
            }
        }
        return null;
    }
}
