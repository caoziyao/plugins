package com.zel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * Description: 实体类
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@Getter
@Setter
@TableName(value = "tb_article")
public class Article {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "article_url")
    private String articleUrl;

    @TableField(value = "column_id")
    private Integer columnsId;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    // 专栏
    private Column column;

    //private List<ETeacher> teachers;

}
