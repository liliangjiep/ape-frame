package com.ape.user.convert;


import com.ape.user.model.entity.User;
import com.ape.user.model.entity.dto.PageUserDto;
import com.ape.user.model.entity.request.UserPageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 21:29
 * version :1.0
 **/
@Mapper
public interface PageUserDtoConvert {
    PageUserDtoConvert INSTANCE = Mappers.getMapper(PageUserDtoConvert.class);

    User buildUser(UserPageRequest pageRequest);
    PageUserDto convertPageReqToDto(UserPageRequest pageRequest);

}
