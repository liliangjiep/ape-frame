package com.ape.user.convert;

import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.PageUserDto;
import com.ape.user.model.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 23:27
 * version :1.0
 **/
@Mapper
public interface PageUserPoConvert {
    PageUserPoConvert INSTANCE = Mappers.getMapper(PageUserPoConvert.class);

    User convertDtoToPo(UserDto pageUserDto);

    User convertPageDtoToPo(PageUserDto pageUserDto);
}
