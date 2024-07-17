package com.example.springbootdemomytool.utils.httpclientdemo.okhttpdemo;

import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName OkHttpDemo1
 * @Description
 * @Author H
 * @Date 2021/9/23 11:43
 * @Version 1.0
 */
public class OkHttpDemo1 {
    public static void main(String[] args) {
        //get("https://www.baidu.com/");

        String url = "http://localhost:9901/api/hsjry/easyflow/capital/imageUpload";
        String requestJson = "";
        postForJson(url, requestJson);

    }


    public static void get(String url) {
        // 1.获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2.设置请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        // 3.封装call
        Call call = client.newCall(request);
        // 4.异步调用，并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {

                }
            }
        });

        // 同步调用，返回Response，会抛出IO异常
        // Response response = call.execute();
    }

    public static void postFormData(String url) {
        // 1. 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2. 构建参数
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123")
                .build();
        // 3. 构建request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        // 4. 将Request封装为Call
        Call call = client.newCall(request);
        // 5. 异步调用
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {

                }
            }
        });
    }

    /**
     * json 参数形式
     *
     * @param url
     * @param json
     */
    public static void postForJson(String url, String json) {
        // 1. 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2. 构建参数
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        // 3. 构建request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        // 4.将Request封装为Call
        Call call = client.newCall(request);
        // 5. 异步调用
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // ...
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    // ...
                }
            }
        });
    }

    /**
     * 文件下载
     *
     * @param url
     * @param target
     * @param fileName
     */
    public void download(String url, String target, String fileName) {
        // 1. 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2. 构建request
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 3. 异步调用
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            // 4. 文件下载
                            downloadfile(response, target, fileName);
                        }
                    }
                });
    }

    private void downloadfile(Response response, String url, String fileName) {
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream outputStream = null;
        try {
            inputStream = response.body().byteStream();
            // 文件大小
            long total = response.body().contentLength();
            File file = new File(url, fileName);
            outputStream = new FileOutputStream(file);
            long sum = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件上传
     *
     * @param url
     */
    public void upload(String url) {
        File file = new File("D:/test.txt");
        if (!file.exists()) {
            System.out.println("文件不存在");
        } else {
            // 获取OkHttpClient对象
            OkHttpClient client = new OkHttpClient();
            // 2.封装参数以form形式
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", "admin")
                    .addFormDataPart("password", "123456")
                    .addFormDataPart("file", "555.txt", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            // 3. 封装request
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            // 4. 异步回调
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                        }
                    });
        }
    }


}
