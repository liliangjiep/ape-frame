package com.ape.user.mapper;

import com.ape.user.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:38
 * version :1.0
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    IPage<User> getUserPage(@Param("po") User user,
                            IPage<User> userPoPage);;
}
