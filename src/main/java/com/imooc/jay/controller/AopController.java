package com.imooc.jay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

    @RequestMapping("/test")
    public String test(String str) {
        return str;
    }

    @RequestMapping("/test2")
    public String test2() {
        return "hello world";
    }
}
