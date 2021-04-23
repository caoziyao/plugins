package com.zel.market.app.controller.column;

import com.zel.market.common.Response;
import com.zel.market.app.service.column.ColumnService;
import com.zel.pojo.entity.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/3
 */
@RestController
@RequestMapping(value = "/api/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @GetMapping(value = "/list")
    public Response list() {
        List<Column> columns = columnService.allColumn();
        ColumnListVO vo = new ColumnListVO();
        vo.setColumn(columns);
        return Response.ok(vo);
    }
}
