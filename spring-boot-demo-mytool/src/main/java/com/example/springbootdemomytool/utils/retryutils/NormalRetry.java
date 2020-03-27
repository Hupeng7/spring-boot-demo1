package com.example.springbootdemomytool.utils.retryutils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RetryUtil
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 14:52
 * @Version 1.0
 */
public class NormalRetry {

    /**
     * 1.普通重试
     *
     * @param count
     */
    public void normalRetry(int count) {
        if (count < 5) {
            count += 1;
            try {
                // do something
            } catch (Exception e) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                // 重试
                normalRetry(count);
            }
        }
    }


}
