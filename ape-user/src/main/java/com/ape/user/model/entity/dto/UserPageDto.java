package com.ape.user.model.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/20 23:31
 * version :1.0
 **/
@Data
public class UserPageDto implements Serializable {
    private static final long serialVersionUID = -6795425868559247780L;
    private String name;

    private Integer age;

    private Integer pageIndex;

    private Integer pageSize;
}
