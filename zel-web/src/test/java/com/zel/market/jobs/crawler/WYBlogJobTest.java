package com.zel.market.jobs.crawler;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/24
 */
class WYBlogJobTest  extends BaseTest {

    @Autowired
    private WYBlogJob wyBlogJob;

    @Test
    void getArticle() {
        wyBlogJob.getArticle();
    }
}