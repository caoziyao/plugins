package com.zel.market.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/6/25
 */
@Setter
@Getter
public class MailTaskDTO {
    private String to;
    private String subject;
    private String content;
    private String type;

    @Override
    public String toString() {
        return "MailTask{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
