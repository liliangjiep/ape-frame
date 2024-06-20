package com.ape.user.service.impl;

import com.ape.entity.PageResult;
import com.ape.user.mapper.UserMapper;
import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.dto.UserPageDto;
import com.ape.user.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public PageResult<User> getUserPage(UserPageDto userPageDto) {
        User user = new User();
        BeanUtils.copyProperties(userPageDto, user);
        // 首先创建一个Page对象，传入当前偏移量和页面大小
        IPage<User> userPoPage = new Page<>(userPageDto.getPageIndex(), userPageDto.getPageSize());
        IPage<User> userPage = userMapper.getUserPage(user, userPoPage);
        PageResult<User> userPoPageResult = new PageResult<>();
        // 调用自定义的分页工具类PageResult，将结果进行装载
        userPoPageResult.loadData(userPage);
        return userPoPageResult;
    }
}
