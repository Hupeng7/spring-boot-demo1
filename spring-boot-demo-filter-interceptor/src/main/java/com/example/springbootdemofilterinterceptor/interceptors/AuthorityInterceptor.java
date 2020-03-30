package com.example.springbootdemofilterinterceptor.interceptors;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName AuthorityInterceptor
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 12:12
 * @Version 1.0
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // start 方法注解拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有@LoginRequired注解  需要认证
        if (methodAnnotation != null) {
            // 这写你拦截需要干的事，比如去缓存，SESSION 权限判断
            System.out.println("======================AuthorityInterceptor do something()============================");
            return true;
        }
        return true;
    }
}
