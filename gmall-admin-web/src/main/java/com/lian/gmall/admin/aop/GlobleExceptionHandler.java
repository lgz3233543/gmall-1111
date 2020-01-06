package com.lian.gmall.admin.aop;

import com.lian.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理所有异常，给前端返回500的 json
 */
@Slf4j
@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    public Object handlerException(Exception exception){
        log.error("系统异常信息感知:{}", exception.getMessage());
        return new CommonResult().validateFailed("数学运算异常...");
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public Object handlerException2(Exception exception){
        log.error("系统异常信息感知:{}", exception.getMessage());
        return new CommonResult().validateFailed("空指针异常...");
    }
}
