package com.zel.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description: 创建User表

 IdType类中枚举解释:

 AUTO(0),           // 数据库id自增
 NONE(1),           // 未设置主键
 INPUT(2),          // 手动输入
 ID_WORKER(3),      // 默认的全局唯一id
 UUID(4),           // 全局唯-id uuid
 ID_WORKER_STR(5);  // ID_WORKER 字符串表示法

 */

@TableName(value = "user")
@Getter
@Setter
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

    //@TableField(value = "status")
    //1-禁用
    private int status;

    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    // FieldFill自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
