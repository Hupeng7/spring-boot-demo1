package com.demo.dynamic.datasource.aspect;

import com.demo.dynamic.datasource.annotation.DefaultDatasource;
import com.demo.dynamic.datasource.datasource.DatasourceConfigContextHolder;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName DatasourceSelectorAspect
 * @Description
 * @Author Leo
 * @Date 2019/10/23 16:38
 * @Version 1.0
 */

@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DatasourceSelectorAspect {

    @Pointcut("execution(public * com.demo.dynamic.datasource.controller.*.*(..))")
    public void datasourcePointcut() {
    }

    /**
     * 前置操作 拦截具体请求 获取header里的数据源id 设置线程变量里 用于后续切换数据源
     *
     * @param joinPoint
     */
    @Before("datasourcePointcut()")
    public void deBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        DefaultDatasource annotation = method.getAnnotation(DefaultDatasource.class);
        if (null != annotation) {
            DatasourceConfigContextHolder.setDefaultDatasource();
        } else {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            String configIdInHeader = request.getHeader("Datasource-Config-Id");
            if (StringUtils.hasText(configIdInHeader)) {
                long configId = Long.parseLong(configIdInHeader);
                DatasourceConfigContextHolder.setCurrentDatasourceConfig(configId);
            } else {
                DatasourceConfigContextHolder.setDefaultDatasource();
            }
        }
    }

    /**
     * 后置操作 设置回默认的数据源id
     */
    @AfterReturning("datasourcePointcut()")
    public void doAfter() {
        DatasourceConfigContextHolder.setDefaultDatasource();
    }


}
