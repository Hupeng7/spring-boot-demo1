package com.example.springbootdemofilterinterceptor.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 12:09
 * @Version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            // 不需要拦截直接过
            return true;
        } else {
            // 这写你拦截需要干的事，比如缓存，SESSION，权限判断等
            System.out.println("==============LoginInterceptor do something()==================");
            return true;
        }
    }
}
