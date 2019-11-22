package com.demo.email.service;

import cn.hutool.core.io.resource.ResourceUtil;
import com.demo.email.SpringBootDemoEmailApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import java.net.URL;


/**
 * @ClassName MailServiceTest
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/18 18:24
 * @Version 1.0
 */
public class MailServiceTest extends SpringBootDemoEmailApplicationTests {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ApplicationContext context;

    /**
     * 测试简单邮件
     */
    @Test
    public void sendSimpleMail() {
        for (int i = 1; i <= 10; i++) {
            mailService.sendSimpleMail("1321781093@qq.com", "这是一封简单邮件", "这是第" + i + "封普通的SpringBoot测试邮件,还敢不理我，下次就10000条了");
        }
    }

    /**
     * 测试HTML邮件
     *
     * @throws MessagingException
     */
    @Test
    public void sendHtmlMail() throws MessagingException {
        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Leo");
        context.setVariable("url", "http://www.baidu.com");
        String emailTemplate = templateEngine.process("welcome", context);
        mailService.sendHtmlMail("1321781093@qq.com", "This is a template HTML email", emailTemplate);
    }

    /**
     * 测试HTML邮件，自定义模板目录
     *
     * @throws MessagingException
     */
    @Test
    public void sendHtmlMail2() throws MessagingException {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/email/");
        templateResolver.setSuffix(".html");

        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Leo");
        context.setVariable("url", "http://www.baidu.com");

        String emailTemplate = templateEngine.process("test", context);
        mailService.sendHtmlMail("1321781093@qq.com", "This is a template HTML email by yourself", emailTemplate);
    }

    /**
     * 测试附件邮件
     *
     * @throws MessagingException
     */
    @Test
    public void sendAttachmentsMail() throws MessagingException {
        URL resource = ResourceUtil.getResource("static/xkcoding.png");
        mailService.sendAttachmentsMail("1321781093@qq.com", "这是一封带附件的邮件", "邮件中有附件，请注意查收！", resource.getPath());
    }

    @Test
    public void sendResourceMail() throws MessagingException {
        String rscId = "xkcoding";
        String content = "<html><body>这是带静态资源的邮件<br/><img src=\'cid:" + rscId + "\'></body></html>";
        URL resource = ResourceUtil.getResource("static/xkcoding.png");
        mailService.sendResourceMail("1321781093@qq.com", "这是一封带静态资源的邮件", content, resource.getPath(), rscId);
    }

}
