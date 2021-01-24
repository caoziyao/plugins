package com.zel.market.service.column;

import com.zel.market.BaseTest;
import com.zel.pojo.entity.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/3
 */
class ColumnServiceTest extends BaseTest {

    @Autowired
    private ColumnService columnService;

    @Test
    void allColumn() {
        List<Column> columns = columnService.allColumn();
        System.out.println(columns);
    }
}