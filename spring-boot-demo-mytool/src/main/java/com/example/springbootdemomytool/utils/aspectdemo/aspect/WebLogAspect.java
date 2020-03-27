package com.example.springbootdemomytool.utils.aspectdemo.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName WebLogAspect
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 17:23
 * @Version 1.0
 */
@Aspect
@Component
@Profile({"dev", "test", "qa"})
@Slf4j
public class WebLogAspect {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Pointcut("@annotation(com.example.springbootdemomytool.utils.aspectdemo.aspect.WebLog)")
    public void webLog() {
    }

    /**
     * 在切入之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取@WebLog注解的描述
        String methodDescription = getAspectLogDescription(joinPoint);
        // 打印请求相关参数
        log.info("================================== Start =====================================");
        // 打印请求 url
        log.info("URL                        :{}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description                :{}", methodDescription);
        // 打印Http method
        log.info("Http Method                :{}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method               :{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求 IP
        log.info("IP                         :{}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args               :{}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 在切入点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        log.info("================================== End =====================================");
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //打印出参
        log.info("Response Args               :{}", new Gson().toJson(result));
        //打印出参
        log.info("Time-Consuming              :{} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}
