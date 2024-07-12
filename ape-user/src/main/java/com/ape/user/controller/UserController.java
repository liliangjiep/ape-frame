package com.ape.user.controller;

import com.ape.bean.Result;
import com.ape.user.convert.PageUserDtoConvert;
import com.ape.user.convert.UserDtoConvert;
import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.PageUserDto;
import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.dto.UserPageDto;
import com.ape.user.model.entity.request.UserPageRequest;
import com.ape.user.model.entity.request.UserRequest;
import com.ape.user.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return Result.ok(userService.deleteUser(id));
    }
    @PostMapping("/getUserPage")
    public Result getUserPage(@RequestBody UserPageRequest userPageReq) {
        UserPageDto userPageDto = new UserPageDto();
        BeanUtils.copyProperties(userPageReq, userPageDto);
        return Result.ok(userService.getUserPage(userPageDto));
    }
    /**
     * 分页查询
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @PostMapping("/queryByPage")
    public Result<Page<User>> queryByPage(@RequestBody UserPageRequest pageRequest) {
        PageUserDto pageUserDto = PageUserDtoConvert.INSTANCE.convertPageReqToDto(pageRequest);

        return Result.ok(this.userService.queryByPage(pageUserDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userRequest 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody UserRequest userRequest) {
        UserDto userDto = UserDtoConvert.INSTANCE.convertReqToDto(userRequest);

        return ResponseEntity.ok(this.userService.insert(userDto));
    }

    /**
     * 编辑数据
     *
     * @param userRequest 实体
     * @return 编辑结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserRequest userRequest) {
        UserDto userDto = UserDtoConvert.INSTANCE.convertReqToDto(userRequest);

        return ResponseEntity.ok(this.userService.update(userDto));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }
}
