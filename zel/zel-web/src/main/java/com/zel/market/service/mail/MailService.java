package com.zel.market.service.mail;

import lombok.NonNull;
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
    void sendSimpleMail(@NonNull String to, @NonNull String subject, @NonNull String content);

    void sendHtmlMail(@NonNull String to, @NonNull String subject, @NonNull String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String resPath, String resId);

    void mock(String message);

    void addTask(String message);
}
