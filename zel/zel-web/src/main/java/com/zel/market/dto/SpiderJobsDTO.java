package com.zel.market.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/2
 */
@Getter
@Setter
public class SpiderJobsDTO {
    private String type;
    private String url;
    private Date createTime;
}
