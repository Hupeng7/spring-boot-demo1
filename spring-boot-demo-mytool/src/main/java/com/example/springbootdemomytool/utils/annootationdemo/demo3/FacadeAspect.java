package com.example.springbootdemomytool.utils.annootationdemo.demo3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName FacadeAspect
 * @Description
 * @Author hup
 * @Date 2020/11/9 15:02
 * @Version 1.0
 */
@Aspect
@Component
public class FacadeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(FacadeAspect.class);

    @Autowired
    HttpServletRequest request;

    @Around("@annotation(com.example.springbootdemomytool.utils.annootationdemo.demo3.Facade)")
    public Object facade(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        Class returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getReturnType();
        // 循环遍历所有参数 进行参数校验
        for (Object parameter : args) {
            try {
                BeanValidator.validateObject(parameter);
            } catch (ValidationException e) {
                return getFailedResponse(returnType, e);
            }
        }

        try {
            // 目标方法执行
            Object response = proceedingJoinPoint.proceed();
            return response;
        } catch (Throwable throwable) {
            return getFailedResponse(returnType, throwable);
        }
    }

    /**
     * 定义并返回一个通用的失败响应
     *
     * @param returnType
     * @param e
     * @return
     */
    private Object getFailedResponse(Class returnType, Throwable throwable) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        // 如果返回值的类型为BaseResponse的子类，则创建一个通用的失败响应
        if (returnType.getDeclaredConstructor().newInstance() instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) returnType.getDeclaredConstructor().newInstance();
            response.setSuccess(false);
            response.setResponseMessage(throwable.toString());
            response.setResponseCode(GlobalConstant.BIZ_ERROR);
            return response;
        }
        LOGGER.error("failed to getFailedResponse , returnType ("+returnType+") is not instanceof BaseResponse");

        return null;
    }


}
