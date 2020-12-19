package com.zel.market.service;

import lombok.NonNull;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
public interface MailService {
    void sendSimpleMail(@NonNull String to, @NonNull String subject, @NonNull String content);

    void sendHtmlMail(@NonNull String to, @NonNull String subject, @NonNull String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String resPath, String resId);
}
