package com.example.springbootdemomytool.utils.resttemplate;

import com.example.springbootdemomytool.beans.Notice;
import com.example.springbootdemomytool.beans.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestRestTemplate
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/22 16:14
 * @Version 1.0
 */
@Component
public class TestRestTemplate {

    @Deprecated
    @Test
    public void restTemplateGetTest() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 将指定的url返回的参数自动封装到自定义好的对应类对象中
            Notice notice = restTemplate.getForObject("http://www.baidu.com", Notice.class);
            System.out.println(notice);
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("http客户端请求出错了");
            // 开发中可以统一异常处理 或者在业务逻辑的catch中作响应
        }
    }

    @Test
    public void restTemplateGetTest1() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> map = new HashMap<>();
            map.put("start", "1");
            map.put("page", "5");
            Notice notice = restTemplate.getForObject("http://fantj.top/notice/list/", Notice.class, map);
            System.out.println(notice);
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    @lombok.Data
    static class InnerRes {
        private Status status;
        private Data result;
    }

    @lombok.Data
    static class Status {
        int code;
        String msg;
    }

    @lombok.Data
    static class Data {
        long id;
        String theme;
        String title;
        String dynasty;
        String explain;
        String content;
        String author;
    }

    @Test
    public void testGet() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top/detail?id=666106231640";
        InnerRes res = restTemplate.getForObject(url, InnerRes.class);
        System.out.println(res);

        // 使用方法二，传参替换
        url = "https://story.hhui.top/detail?id={?}";
        res = restTemplate.getForObject(url, InnerRes.class, "666106231640");
        System.out.println(res);

        // 使用方法三，map传参
        url = "https://story.hhui.top/detail?id={id}";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 666106231640L);
        res = restTemplate.getForObject(url, InnerRes.class, params);
        System.out.println("map 传参：" + res);

        // 使用方法四 URI访问
        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
        res = restTemplate.getForObject(uri, InnerRes.class);
        System.out.println("URI访问：" + res);
    }

    @Test
    public void testGetForEntity() {
        String url = "https://story.hhui.top/detail?id=666106231640";
        ResponseEntity<InnerRes> res = restTemplate.getForEntity(url, InnerRes.class);
        System.out.println("get for entity:" + res);
    }

    @Test
    public void testPost() {
        String url = "http://localhost:9090/restTemplate/post";
        String email = "test@hello.top";
        String nick = "zhangsan";
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("email", email);
        request.add("nick", nick);

        // 使用方法三
        URI uri = URI.create(url);
        String ans = restTemplate.postForObject(uri, request, String.class);
        System.out.println("post rest template: " + ans);

        // 使用方法一
        ans = restTemplate.postForObject(url, request, String.class);
        System.out.println("method one is :" + ans);

        // 使用方法一，但是结合表单参数和uri参数的方式，其中uri参数的填充和get请求一致
        request.clear();
        request.add("email", email);
        ans = restTemplate.postForObject(url + "?nick={?}", request, String.class, nick);
        System.out.println(ans);

        // 使用方法二
        Map<String, String> params = new HashMap<>();
        params.put("nick", nick);
        ans = restTemplate.postForObject(url + "?nick={nick}", request, String.class, params);
        System.out.println("method two is: " + ans);

    }

    @Test
    public void testConfiguration() {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(Hello.class);
//        Hello hello = annotationContext.getBean("testHello1", Hello.class);// 创建bean的引用对象
//        User user = hello.testHello();
        User user = annotationContext.getBean("testHello1", User.class);
        System.out.println(user);
    }

    @Autowired
    @Qualifier(value = "userHello")
    private User userBean;

    @Test
    public void testConfigurationBean() {
        System.out.println(userBean);
        Long id = userBean.getId();
        System.out.println("id: " + id);
    }


}
