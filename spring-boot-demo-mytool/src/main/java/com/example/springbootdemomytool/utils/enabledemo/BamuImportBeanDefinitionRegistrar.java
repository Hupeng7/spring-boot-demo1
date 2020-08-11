package com.example.springbootdemomytool.utils.enabledemo;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BamuImportBeanDefinitionRegistrar
 * @Description TODO
 * @Author Leo
 * @Date 2020/7/6 16:56
 * @Version 1.0
 */
public class BamuImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 获取EnableEcho 注解所有的属性的value
        Map<String,Object> attributes = annotationMetadata.getAnnotationAttributes(EnableEcho.class.getName());
        // 获取package属性的value
        List<String> packages = Arrays.asList((String[]) attributes.get("packages"));

        // 使用beanDefinitionRegistry对象将EchoBeanPostProcessor注入至Spring容器中
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(EchoBeanPostProcessor.class);
        // 给EchoBeanPostProcessor.class中注入packages
        beanDefinitionBuilder.addPropertyValue("packages",packages);

        beanDefinitionRegistry.registerBeanDefinition(EchoBeanPostProcessor.class.getName(),beanDefinitionBuilder.getBeanDefinition());
    }
}
