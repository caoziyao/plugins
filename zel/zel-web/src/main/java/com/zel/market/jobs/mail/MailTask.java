package com.zel.market.jobs.mail;

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
@Builder
public class MailTask {

    private String to;
    private String subject;
    private String content;
    private String type;
}
