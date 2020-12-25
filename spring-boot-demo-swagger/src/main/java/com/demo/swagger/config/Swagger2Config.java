package com.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Swagger2Config
 * @Description 1. swagger配置
 * 2. api接口请求头添加jwt
 * @Author Leo
 * @Date 2019/10/18 16:21
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 全局api需要jwt
                .globalOperationParameters(jwtToken())
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.demo.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("spring-boot-demo")
                .description("this is a sample demo for Swagger API")
                .contact(new Contact("Leo", "http://www.baidu.com", ""))
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    private List<Parameter> jwtToken() {
        String jwt = "Bearer {jwt}";

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        // 声明key
        tokenPar.name("Authorization")
                // 文字说明
                .description("jwt令牌")
                // 类型为字符串
                .modelRef(new ModelRef("string"))
                // 参数形式为header参数
                .parameterType("header")
                // 默认值
                .defaultValue(jwt)
                // 是否必须
                .required(false);
        parameters.add(tokenPar.build());
        return parameters;
    }


}
