package com.zel.pojo.entity;

//import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


//@TableName(value = "book")
@Getter
@Setter
public class Book {

    //@TableId(value = "id", type= IdType.AUTO)
    private Long id;

    //@TableField(value = "book")
    private String book;

    //@TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    // FieldFill自动填充
    //@TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book='" + book + '\'' +
                '}';
    }
}
