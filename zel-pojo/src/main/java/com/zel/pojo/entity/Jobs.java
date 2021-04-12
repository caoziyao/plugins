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
 * @since 2020/4/20
 */
@Getter
@Setter
@TableName(value = "jobs")
public class Jobs {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "cron_key")
    private String cronKey;

    @TableField(value = "cron_expression")
    private String cronExpression;

    @TableField(value = "task_explain")
    private String taskExplain;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    // FieldFill自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
