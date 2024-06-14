package com.ape.user.model.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:55
 * version :1.0
 **/
@Data
public class UserRequest implements Serializable {
    private static final long serialVersionUID = -3456286604743125270L;
    private String name;

    private Integer age;
}
