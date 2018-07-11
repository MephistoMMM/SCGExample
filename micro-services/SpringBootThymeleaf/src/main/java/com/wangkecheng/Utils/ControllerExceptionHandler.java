package com.wangkecheng.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kanson on 2018/7/13.
 * 控制器的异常处理类
 */
//这个注解是指这个类是处理其他controller抛出的异常
@ControllerAdvice
public class ControllerExceptionHandler {

    //这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler(UserNotExistException.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)     //服务内部错误
    public Map<String,Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}