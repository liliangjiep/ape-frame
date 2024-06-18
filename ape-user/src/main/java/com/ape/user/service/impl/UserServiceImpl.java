package com.ape.user.service.impl;

import com.ape.user.mapper.UserMapper;
import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/15 0:00
 * version :1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        int count = userMapper.insert(user);
        return count;
    }

    @Override
    public int deleteUser(Integer id) {
        int count = userMapper.deleteById(id);
        return count;
    }
}
