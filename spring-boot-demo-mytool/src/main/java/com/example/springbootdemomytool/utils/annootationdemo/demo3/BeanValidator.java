package com.example.springbootdemomytool.utils.annootationdemo.demo3;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ClassName BeanValidator
 * @Description 参数校验工具
 * 使用HibernateValidator + 自定义注解 + AOP实现参数校验的方式。
 * @Author hup
 * @Date 2020/11/9 11:51
 * @Version 1.0
 */
public class BeanValidator {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
            .buildValidatorFactory().getValidator();


    public static void validateObject(Object object, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations.stream().findFirst().isPresent()) {
            throw new ValidationException(constraintViolations.stream().findFirst().get().getMessage());
        }

    }

}
