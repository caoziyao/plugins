package com.zel.pojo.entity.enums;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/3
 */
public enum  EColumnType {

    wangyin(1, "王银"),
    mysql(2, "mysql"),
    reids(3, "redis"),
    java(4, "java"),
    md(5, "消息队列"),
    other(6, "其他"),

    ;

    private int id;
    private String name;

    EColumnType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
