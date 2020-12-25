package com.demo.rabbitmq.demo.util;

import com.demo.rabbitmq.demo.pojo.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @ClassName MailUtil
 * @Description
 * @Author H
 * @Date 2020/12/23 17:58
 * @Version 1.0
 */
@Component
@Slf4j
public class MailUtil {
    @Value("${spring.mail.from}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param mail
     * @return
     */
    public boolean send(Mail mail) {
//        if (true) {
//            return false;
//        }
        String to = mail.getTo();
        String title = mail.getTitle();
        String content = mail.getContent();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("邮件发送成功");
            return true;
        } catch (MailException e) {
            log.error("邮件发送失败,to : {},title: {}", to, title, e);
            return false;
        }
    }

    /**
     * 发送附件邮件
     *
     * @param mail 邮件
     * @param file 附件
     * @return
     */
    public boolean sendAttachment(Mail mail, File file) {
        String to = mail.getTo();
        String title = mail.getTitle();
        String content = mail.getContent();

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            FileSystemResource resource = new FileSystemResource(file);
            String fileName = file.getName();
            helper.addAttachment(fileName, resource);
            mailSender.send(message);
            log.info("附件邮件发送成功");
            return true;
        } catch (MessagingException e) {
            log.error("附件邮件发送失败,to : {}, title: {}", to, title, e);
            return false;
        }
    }


}
