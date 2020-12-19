package com.zel.market.utils;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
class HTMLParseUtilsTest extends BaseTest {

    @Autowired
    HTMLParseUtils htmlParseUtils;

    @Test
    void parse() {
        htmlParseUtils.parse();
    }
}