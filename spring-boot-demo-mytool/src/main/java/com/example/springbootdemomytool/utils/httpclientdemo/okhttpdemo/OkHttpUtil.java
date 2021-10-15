package com.example.springbootdemomytool.utils.httpclientdemo.okhttpdemo;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName OkHttpUtil
 * @Description
 * @Author H
 * @Date 2021/9/23 15:31
 * @Version 1.0
 */
public class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    private static OkHttpClient okHttpClient;

    @Autowired
    public OkHttpUtil(OkHttpClient okHttpClient) {
        OkHttpUtil.okHttpClient = okHttpClient;
    }

    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer stringBuffer = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    stringBuffer.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    stringBuffer.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .url(stringBuffer.toString())
                .build();
        try (Response response = okHttpClient.newCall(request).execute();) {
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }


    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        // 添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }

    public static String getForHeader(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }

    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }

    public static String postXmlParams(String url, String xml) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml;charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }


}
