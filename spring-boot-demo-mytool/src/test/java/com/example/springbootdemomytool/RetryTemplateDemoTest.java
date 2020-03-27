package com.example.springbootdemomytool;

import com.example.springbootdemomytool.utils.retryutils.RetryTemplateDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

/**
 * @ClassName RetryTemplateDemoTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 16:55
 * @Version 1.0
 */
public class RetryTemplateDemoTest extends SpringBootDemoMytoolApplicationTests {

    @Autowired
    private RetryTemplateDemo retryTemplateDemo;

    @Test
    public void retry() {
        RetryTemplate retryTemplate = retryTemplateDemo.retryTemplate();
        retryTemplate.execute((RetryCallback<Void, RuntimeException>) context -> {
            // dosomething()

            // 模拟抛出异常
            throw new RuntimeException("模拟异常");
        });
    }


}
