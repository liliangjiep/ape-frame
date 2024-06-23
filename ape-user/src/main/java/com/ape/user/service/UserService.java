package com.ape.user.service;

import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.PageUserDto;
import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.dto.UserPageDto;
import com.ape.page.PageResponse;

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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 分页查询
     *
     * @param pageSysUserDto 分页对象
     * @return 查询结果
     */
    PageResponse<User> queryByPage(PageUserDto pageSysUserDto);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(UserDto user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(UserDto user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}
