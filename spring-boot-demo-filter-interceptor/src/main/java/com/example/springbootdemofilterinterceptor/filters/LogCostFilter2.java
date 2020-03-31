package com.example.springbootdemofilterinterceptor.filters;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName LogCostFilter2
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 11:59
 * @Version 1.0
 */
@WebFilter(urlPatterns = "/*", filterName = "logFilter2") // @WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器
public class LogCostFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===============LogCostFilter2 do something()=======================");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LogFilter2 Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
