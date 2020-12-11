package com.zel.dbmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName(value = "book")
public class Book {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "book")
    private String book;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book='" + book + '\'' +
                '}';
    }
}
