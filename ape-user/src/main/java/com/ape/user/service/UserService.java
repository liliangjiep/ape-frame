package com.ape.user.service;

import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.dto.UserPageDto;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:33
 * version :1.0
 **/
public interface UserService {
    int addUser(UserDto userDto);

    int deleteUser(Integer id);

    Object getUserPage(UserPageDto userPageDto);
}
