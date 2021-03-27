package com.zel.market.app.service.column;

import com.zel.dbmanager.mapper.ColumnMapper;
import com.zel.pojo.entity.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/3
 */
@Service
public class ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    public List<Column> allColumn() {
        Map<String, Object> map = new HashMap<>();
        List<Column> columns = columnMapper.selectByMap(map);
        return columns;
    }

}
