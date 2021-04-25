package com.portal.controller;

import com.portal.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyContrillerAdvice {
    @ExceptionHandler(value = MyException.class)
    public Map<String, Object> exceptionHandler(MyException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
