package com.ape.user.model.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:57
 * version :1.0
 **/
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 3687423933005351715L;
    private String name;

    private Integer age;
}
