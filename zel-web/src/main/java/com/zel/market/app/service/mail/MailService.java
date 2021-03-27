package com.zel.market.app.service.mail;

import com.zel.market.dto.MailTaskDTO;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Service
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String resPath, String resId);

    void mock(String message);

    void addTask(MailTaskDTO task);
}
