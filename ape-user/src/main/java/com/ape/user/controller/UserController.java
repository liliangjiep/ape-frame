package com.ape.user.controller;

import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.request.UserRequest;
import com.ape.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:27
 * version :1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Integer addUser(@RequestBody UserRequest userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        int count = userService.addUser(userDto);
        return count;
    }
}
