package com.zel.market.app.controller.column;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zel.pojo.entity.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/3
 */
@Getter
@Setter
public class ColumnListVO {

    @JsonProperty("column")
    private List<Column> column;
}
