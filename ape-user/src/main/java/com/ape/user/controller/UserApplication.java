package com.ape.user.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/10 22:26
 * version :1.0
 **/
@SpringBootApplication
@MapperScan(value = "com.ape.*.mapper")
@ComponentScan(value = "com.ape")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}