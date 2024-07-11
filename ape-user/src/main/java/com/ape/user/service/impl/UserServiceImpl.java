package com.ape.user.service.impl;

import com.ape.entity.PageResult;
import com.ape.page.PageResponse;
import com.ape.user.convert.PageUserDtoConvert;
import com.ape.user.convert.PageUserPoConvert;
import com.ape.user.mapper.UserDao;
import com.ape.user.mapper.UserMapper;
import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.PageUserDto;
import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.dto.UserPageDto;
import com.ape.user.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private UserDao userDao;

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
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageUserDto 分页对象
     * @return 查询结果
     */
    @Override
    public PageResponse<User> queryByPage(PageUserDto pageUserDto) {
        User user = PageUserPoConvert.INSTANCE.convertPageDtoToPo(pageUserDto);
        PageResponse<User> pageResponse = new PageResponse<>();
        pageResponse.setCurrent(pageUserDto.getPageNo());
        pageResponse.setPageSize(pageUserDto.getPageSize());
        Long pageStart = pageResponse.getPageStart();
        long total = this.userDao.count(user);
        List<User> sysUserList = this.userDao.queryAllByLimit(user, pageStart, pageUserDto.getPageSize());
        pageResponse.setTotal(total);
        pageResponse.setRecords(sysUserList);
        return pageResponse;
    }

    /**
     * 新增数据
     *
     * @param userDto 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(UserDto userDto) {
        User user = PageUserPoConvert.INSTANCE.convertDtoToPo(userDto);

        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param userDto 实例对象
     * @return 实例对象
     */
    @Override
    public User update(UserDto userDto) {
        User user = PageUserPoConvert.INSTANCE.convertDtoToPo(userDto);
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }
}
