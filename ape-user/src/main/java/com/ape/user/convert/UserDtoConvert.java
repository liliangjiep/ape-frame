package com.ape.user.convert;

import com.ape.user.model.entity.dto.UserDto;
import com.ape.user.model.entity.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 23:28
 * version :1.0
 **/
@Mapper
public interface UserDtoConvert {
    UserDtoConvert INSTANCE = Mappers.getMapper(UserDtoConvert.class);
    UserDto convertReqToDto(UserRequest pageRequest);
}
