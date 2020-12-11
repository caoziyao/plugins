package com.zel.dbmanager.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "ss_account")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncry() {
        return encry;
    }

    public void setEncry(String encry) {
        this.encry = encry;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPlian() {
        return plian;
    }

    public void setPlian(String plian) {
        this.plian = plian;
    }

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
