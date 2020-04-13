package com.example.springbootdemomytool.utils.apidemo.global;

import com.example.springbootdemomytool.beans.ResultVO;
import com.example.springbootdemomytool.utils.apidemo.exceptions.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName ResponseControllerAdvice
 * @Description 全局处理响应数据
 * @Author Leo
 * @Date 2020/4/13 18:30
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = {"com.example.springbootdemomytool.utils.apidemo.controller"}) // 注意 需要加上扫描的包
public class ResponseControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        // 如果接口返回的类型本身就是ResultVO 那就没有必要进行额外的操作，返回值false
        return !returnType.getGenericParameterType().equals(ResultVO.class);
        // return false;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String 类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在 ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        // 将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
