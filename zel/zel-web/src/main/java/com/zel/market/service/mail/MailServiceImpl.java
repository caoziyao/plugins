package com.zel.market.service.mail;

import com.zel.market.dto.MailTaskDTO;
import com.zel.market.jobs.runner.MailTaskRunner;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void mock(String message) {
        System.out.println("send mail: " + message);
    }

    @Autowired
    private MailTaskRunner mailTask;

    @Override
    public void addTask(MailTaskDTO task) {
        mailTask.add(task);
    }

    /**
     * 发送最简单的文本邮件
     */
    @Override
    public void sendSimpleMail(@NonNull String to, @NonNull String subject, @NonNull String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    @Override
    public void sendHtmlMail(@NonNull String to, @NonNull String subject, @NonNull String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //这里的true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送邮件时发生异常!" + e);
        }
    }

    /**
     * 发送带有附件的邮件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName, fileSystemResource);

            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送邮件时发生异常!" + e);
        }
    }

    /**
     * 发送邮件中带有图片的邮件
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String resPath, String resId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper Helper = new MimeMessageHelper(message, true);
            Helper.setFrom(from);
            Helper.setTo(to);
            Helper.setSubject(subject);
            Helper.setText(content, true);

            FileSystemResource resource = new FileSystemResource(new File(resPath));
            Helper.addAttachment(resId, resource);

            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送邮件时发生异常!" + e);
        }
    }
}
