package com.zel.market.app.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/2
 */
@Getter
@Setter
public class BaseVO {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty("date")
    private Date date;
}
