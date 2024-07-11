package com.ape.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 23:25
 * version :1.0
 **/
@Mapper
public interface UserPoConvert {
    UserPoConvert INSTANCE = Mappers.getMapper(UserPoConvert.class);
}
