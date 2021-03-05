package com.portal.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> handleException(Exception exception) {
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode",500);
        map.put("errorMsg",exception.getMessage());
        return ResponseEntity.ok(map);
    }
}
