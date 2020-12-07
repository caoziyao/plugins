package com.example.emailservice.mq.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: 接收格式
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Data
public class ReceiveDTO {
    // 指定格式，默认是时间戳
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty("createTime")
    Date createTime;

    @JsonProperty("to")
    private String to;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("content")
    private String content;
}
