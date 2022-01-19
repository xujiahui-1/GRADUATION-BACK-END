package com.xujiahui.commonutils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody  //为了返回数据
    public X error(Exception e){
        e.printStackTrace();
        return  X.error().message("执行全局处理异常");
    }
    /**
     * 自定义异常X
     * @param e
     * @return
     */
    @ExceptionHandler(XException.class)
    @ResponseBody  //为了返回数据
    public X error(XException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return  X.error().code(e.getCode()).message(e.getMessage());
    }
}
