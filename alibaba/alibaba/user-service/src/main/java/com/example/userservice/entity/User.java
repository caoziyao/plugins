package com.example.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description: 创建User表
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Data
// @Builder 垃圾
@TableName(value = "user")
public class User {
    // TableId-数据库主键字段
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "age")
    private Integer age;
}
