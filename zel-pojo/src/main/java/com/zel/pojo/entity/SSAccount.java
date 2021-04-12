package com.zel.pojo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName(value = "ss_account")
@Getter
@Setter
public class SSAccount {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "location")
    private String location;

    @TableField(value = "ip")
    private String ip;

    @TableField(value = "port")
    private String port;

    @TableField(value = "password")
    private String password;

    @TableField(value = "encry")
    private String encry;

    @TableField(value = "protocol")
    private String protocol;

    @TableField(value = "plian")
    private String plian;

    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    // FieldFill自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @Override
    public String toString() {
        return "SSAccount{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", encry='" + encry + '\'' +
                ", protocol='" + protocol + '\'' +
                ", plian='" + plian + '\'' +
                '}';
    }
}
