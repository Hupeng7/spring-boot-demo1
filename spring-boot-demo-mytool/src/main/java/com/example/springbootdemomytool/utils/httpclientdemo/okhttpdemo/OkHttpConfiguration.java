package com.example.springbootdemomytool.utils.httpclientdemo.okhttpdemo;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName OkHttpConfiguration
 * @Description
 * @Author H
 * @Date 2021/9/23 15:24
 * @Version 1.0
 */
@Configuration
public class OkHttpConfiguration {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                //.sslSocketFactory(sslSocketFactory(),x509TrustManager())
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }

//    private X509TrustManager x509TrustManager() {
//    }
//
//    private SSLSocketFactory sslSocketFactory() {
//    }


}
