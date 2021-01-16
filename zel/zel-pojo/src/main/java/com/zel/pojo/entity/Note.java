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

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "create_time")
    private Date createTime;
}
