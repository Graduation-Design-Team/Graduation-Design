package com.portal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class controller {

    @GetMapping
    public String test() {
        return "便民微服务测试";
    }
}
