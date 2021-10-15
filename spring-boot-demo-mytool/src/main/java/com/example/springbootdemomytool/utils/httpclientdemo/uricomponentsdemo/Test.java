package com.example.springbootdemomytool.utils.httpclientdemo.uricomponentsdemo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName Test
 * @Description
 * @Author hup
 * @Date 2020/11/11 10:34
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        new Test().handle();
    }

    private void handle() {
        String httpUrl = "https://felord.cn/spring-security/{article}?version=1&timestamp=123123325";

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(httpUrl).build();
        // 如果不是Http就不能使用上面方法，需要使用 fromUriString(String uri)

        // 可以直接构造一个UriComponents
        UriComponents https = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("www.felord,cn")
                .port(8080)
                .path("/spring-boot/{article}")
                .queryParam("version", "9527")
                .encode(StandardCharsets.UTF_8)
                .build();
        // https://www.felord.cn:8080/spring-boot/{article}?version=9527

        // 提取协议头
        String scheme = uriComponents.getScheme();
        System.out.println("scheme = " + scheme);

        // 提取host
        String host = uriComponents.getHost();
        System.out.println("host = " + host);

        // 提取Port
        int port = uriComponents.getPort();
        System.out.println("port = " + port);

        // 提取Path
        String path = uriComponents.getPath();
        System.out.println("path = " + path);

        // 提取Query
        String query = uriComponents.getQuery();
        System.out.println("query = " + query);

        // 更加合理的方式
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        System.out.println("queryParams = " + queryParams);

        // 填充路径参数
        UriComponents expand = uriComponents.expand("oauth2-authorization-request.html");
        System.out.println("expand = " + expand);

    }

}
