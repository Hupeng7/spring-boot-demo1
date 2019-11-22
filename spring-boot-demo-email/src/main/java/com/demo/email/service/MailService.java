package com.demo.email.service;

import javax.mail.MessagingException;

/**
 * @author Leo
 */
public interface MailService {

    /**
     * 发送文本邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    void sendSimpleMail(String to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param cc
     * @throws MessagingException
     */
    void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @param cc
     * @throws MessagingException
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) throws MessagingException;

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param recPath
     * @param rscId
     * @param cc
     * @throws MessagingException
     */
    void sendResourceMail(String to, String subject, String content, String recPath, String rscId, String... cc) throws MessagingException;

}
