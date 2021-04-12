package com.zel.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/16
 */
@Getter
@Setter
@TableName(value = "tb_note")
public class Note {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    // FieldFill自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
