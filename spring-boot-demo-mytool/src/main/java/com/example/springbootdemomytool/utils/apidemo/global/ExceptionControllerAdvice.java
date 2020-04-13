package com.example.springbootdemomytool.utils.apidemo.global;

import com.example.springbootdemomytool.beans.ResultVO;
import com.example.springbootdemomytool.enums.ResultCode;
import com.example.springbootdemomytool.utils.apidemo.exceptions.APIException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionControllerAdvice
 * @Description 全局异常处理
 * @Author Leo
 * @Date 2020/4/13 17:30
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        // return objectError.getDefaultMessage();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // return e.getMsg();
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

}
