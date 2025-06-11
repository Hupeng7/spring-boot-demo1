package com.demo.ratelimit.redis.controller;

import cn.hutool.core.lang.Dict;
import com.demo.ratelimit.redis.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/9 17:00
 * @Version 1.0
 */
@Slf4j
@RestController
public class TestController {

    @RateLimiter(value = 5)
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public Dict test1(@RequestParam(value = "aa") String aa,
                      @RequestParam(value="bb") String bb) {
        log.info("【test1】 被执行了。。。");
        log.info("aa:{}",aa);
        log.info("bb:{}",bb);
        return Dict.create().set("msg", "hello,world!").set("description", "别一直想看到我，不信你快速刷新看看");
    }

    @GetMapping("/test2")
    public Dict test2() {
        log.info("【test2】被执行了。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "我一直都在，不离不弃");
    }

    @RateLimiter(value = 2, key = "测试自定义的key")
    @GetMapping("/test3")
    public Dict test3() {
        log.info("【test3】被执行了。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看");
    }

    @RateLimiter(value = 4, key = "测试自定义的key4", timeout = 3)
    @GetMapping("/test4")
    public Dict test4() {
        log.info("【test4】被执行了。。。");
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看");
    }

    @RateLimiter(value = 4, key = "测试自定义的key5", timeout = 3)
    @GetMapping("/test5")
    public Dict test5(@RequestParam(value = "aa") String aa,
                      @RequestParam(value="bb") String bb) {
        log.info("【test5】被执行了。。。");
        log.info("aa:{}",aa);
        log.info("bb:{}",bb);
        return Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看");
    }


}
