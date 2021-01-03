package com.zel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@Getter
@Setter
@TableName(value = "tb_column")
public class Column {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "children")
    private String children;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "create_time")
    private Date createTime;
}
