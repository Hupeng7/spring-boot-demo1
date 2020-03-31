package com.example.springbootdemomytool.utils.restfuldemo.aspect;

import com.example.springbootdemomytool.utils.restfuldemo.beans.ResultBean;
import com.sun.xml.internal.ws.api.model.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName ControllerAOP
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 18:54
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    /**
     * 定义切入点，切入点为 com.example.springbootdemomytool.utils.restfuldemo.controller中所有函数
     * 通过@Pointcut 注解声明频繁使用的切入点表达式
     * 1.通配符 * ,该通配符主要用于匹配单个单词，或者是以某个词为前缀或后缀的单词
     * 如下示例表示返回值为任意类型，在com.spring.service.BusinessObject类中，并且参数个数为零的方法：
     * p1 某个类下所有无参方法              execution(* com.spring.service.BusinessObject.*())
     * 下述示例表示返回值为任意类型，在com.spring.service包中，以Business为前缀的类，并且是类中参数个数为零方法：
     * p2 Business为前缀的类下所有无参方法   execution(* com.spring.service.Business*.*())
     * 2.通配符 ..,..通配符，该通配符表示0个或多个项，主要用于declaring-type-pattern和param-pattern中，如果用于declaring-type-pattern中，
     * 则表示匹配当前包及其子包，如果用于param-pattern中，则表示匹配0个或多个参数。
     * 如下示例表示匹配返回值为任意类型，并且是com.spring.service包及其子包下的任意类的名称为businessService的方法，而且该方法不能有任何参数：
     * p3 某包下指定businessService类名下无参方法  execution(* com.spring.service..*.businessService())
     * 这里需要说明的是，包路径service..*.businessService()中的..应该理解为延续前面的service路径，表示到service路径为止，或者继续延续service路径，
     * 从而包括其子包路径；后面的*.businessService()，这里的*表示匹配一个单词，因为是在方法名前，因而表示匹配任意的类。
     * 如下示例是使用..表示任意个数的参数的示例，需要注意，表示参数的时候可以在括号中事先指定某些类型的参数，而其余的参数则由..进行匹配：
     * p4 某类下指定参数的方法              execution(* com.spring.service.BusinessObject.businessService(java.lang.String,..))
     * p5 指定包下所有类任意参数的方法表达式  execution(* com.spring.service..*(..))
     * p6 注解则为 @Pointcut("@annotation(com.example.springbootdemomytool.utils.aspectdemo.aspect.WebLog)")
     * 其中WebLog为注解名称
     */
    // @Pointcut("execution(public * com.example.springbootdemomytool.utils.restfuldemo.controller.TestAopController.*(..))")
    @Pointcut("execution(public * com.example.springbootdemomytool.utils.restfuldemo..*(..))")
    public void ControllerAspect() {}

    @Around("ControllerAspect()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> resultBean;

        try {
            resultBean = (ResultBean<?>) joinPoint.proceed();
            log.info(joinPoint.getSignature() + " use time:" + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Throwable e) {
            resultBean = handlerException(joinPoint, e);
        }
        return resultBean;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint joinPoint, Throwable e) {
        ResultBean<?> resultBean = new ResultBean<>();

        if (e instanceof CheckedException) {
            resultBean.setMsg(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.FAIL);
        } else {
            log.error(joinPoint.getSignature() + " error ", e);
            resultBean.setMsg(e.toString());
            resultBean.setCode(ResultBean.FAIL);
            // TODO 未知的异常是应该重点关注的，这里可以做其他操作，如邮件通知，单独写到某个文件等等
        }
        return resultBean;
    }


}
