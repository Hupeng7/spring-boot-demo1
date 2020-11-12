package com.example.springbootdemomytool.utils.annootationdemo.demo2;

import com.google.common.base.CaseFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName OpLogAspect
 * @Description
 * 简单的使用自定义注解+切面进行日志记录的场景
 * 1、使用@Around注解来指定对标注了OpLog的方法设置切面。
 * 2、使用Spel的相关方法，通过指定的表示，从对应的参数中获取到目标对象的唯一性标识。
 * 3、再方法执行成功后，输出日志。
 * @Author hup
 * @Date 2020/11/9 10:38
 * @Version 1.0
 */

@Aspect
@Component
public class OpLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpLogAspect.class);

    @Autowired
    HttpServletRequest request;

    @Around("@annotation(com.example.springbootdemomytool.utils.annootationdemo.demo2.OpLog)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        OpLog opLog = method.getAnnotation(OpLog.class);
        Object response = null;
        long spendTime = 0L;
        try {
            long startTime = System.currentTimeMillis();
            // 目标方法执行
            response = proceedingJoinPoint.proceed();
            spendTime = System.currentTimeMillis() - startTime;
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }

        if (!StringUtils.isEmpty(opLog.opItemIdExpression())) {
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(opLog.opItemIdExpression());
            EvaluationContext context = new StandardEvaluationContext();

            // 获取参数值
            Object[] args = proceedingJoinPoint.getArgs();
            // 获取运行时参数的名称
            LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

            String[] parameterNames = discoverer.getParameterNames(method);

            // 将参数绑定到context中
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
            }

            // 将方法的resp当作变量放到context中，变量名称为该类名转化为小写字母开头的驼峰形式
            if (response != null) {
                context.setVariable(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, response.getClass().getSimpleName()),
                        response);
            }
            // 解析表达式 ，获取结果
            String itemId = String.valueOf(expression.getValue(context));
            // 执行日志记录
            handle(opLog.opType(), opLog.opItem(), itemId, spendTime);
        }

        return response;
    }

    /**
     * 处理日志打印输出
     *
     * @param opType
     * @param opItem
     * @param opItemId
     * @param spendTime
     */
    private void handle(OpType opType, String opItem, String opItemId, long spendTime) {
        // 通过日志打印输出
        LOGGER.info("opType = " + opType.name() + ",opItem = " + opItem + ",opItemId = " + opItemId + ",spendTime = " + spendTime + " ms");
    }


}
