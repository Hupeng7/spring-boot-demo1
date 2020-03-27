package com.example.springbootdemomytool.utils.retryutils;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ClassProxyRetry
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 15:05
 * @Version 1.0
 */
@Component
@Slf4j
public class ClassProxyRetry {

    private static final int MAX_RETRY_TIMES = 5;
    private static final long RETRY_FIXED_DELAY_MILLISECONDS = 2000;

    public Response doSomething(Object queryParams) {
        int retryTimes = 0;
        Response response = null;
        while (retryTimes < MAX_RETRY_TIMES) {
            try {
                response = (Response) new Object();
                break;
            } catch (Exception e) {
                retryTimes++;
                e.printStackTrace();
                if (retryTimes >= MAX_RETRY_TIMES) {
                    throw e;
                } else {
                    Uninterruptibles.sleepUninterruptibly(RETRY_FIXED_DELAY_MILLISECONDS, TimeUnit.MICROSECONDS);
                }
            }
        }
        return response;
    }

}
