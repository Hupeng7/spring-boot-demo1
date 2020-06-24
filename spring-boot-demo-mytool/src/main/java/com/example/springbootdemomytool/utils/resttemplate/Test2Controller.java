package com.example.springbootdemomytool.utils.resttemplate;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemomytool.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/22 17:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/restTemplate/")
public class Test2Controller {
    /**
     * @Configuration @Bean 注入获取 User
     */
    @Autowired
    @Qualifier(value = "userHello")
    private User userBean;


    @ResponseBody
    @RequestMapping(path = "post", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public String post(HttpServletRequest request,
                       @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "nick", required = false) String nick) {
        System.out.println(userBean);
        Long id = userBean.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("result", "add " + email + " # " + nick + " success!");
        return JSON.toJSONString(map);
    }


}
