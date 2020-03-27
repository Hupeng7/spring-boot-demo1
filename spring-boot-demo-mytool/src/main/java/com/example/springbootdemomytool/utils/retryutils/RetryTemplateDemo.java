package com.example.springbootdemomytool.utils.retryutils;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName RetryTemplateDemo
 * @Description
 * 注解
 * @Retryable
 * @Recover
 * @Backoff
 * 注意
 * Spring Retry有一个缺点，其回退策略，默认使用的是Thread.sleep方法，会导致当前的线程被阻塞，因此使用的时候要注意。
 * 链接：https://www.jianshu.com/p/7a4db0c07978
 * @Author Leo
 * @Date 2020/3/27 16:49
 * @Version 1.0
 */
@Service
public class RetryTemplateDemo {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        // 每次回退固定的时间
//        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
//        fixedBackOffPolicy.setBackOffPeriod(2000l);

        // 指数回退，第一次回退0.2s,第二次回退0.4s
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(200L);
        exponentialBackOffPolicy.setMultiplier(2);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        // 重试策略，有多种重试策略
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        retryTemplate.setThrowLastExceptionOnExhausted(false);

        return retryTemplate;
    }

}
