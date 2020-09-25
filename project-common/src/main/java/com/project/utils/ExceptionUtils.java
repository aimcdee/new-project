package com.project.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全部获取异常信息工具类
 *
 * @author liangyuding
 * @date 2020-09-25
 */
@RestControllerAdvice
@Slf4j
public class ExceptionUtils {

    /**
     * 全局获取异常的堆栈信息
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R exceptionHandler(MethodArgumentNotValidException e) {
        return R.ok(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
