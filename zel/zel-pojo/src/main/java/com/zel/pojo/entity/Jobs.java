package com.zel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

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
}
