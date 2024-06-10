package com.ape.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 貔貅
 * @Description :
 * @Date : 2024/6/10 22:57
 * version :1.0
 **/
@RestController
public class TestController {

    @GetMapping("/test") // 修正了括号
    public String test(){
        return "test";
    }
}