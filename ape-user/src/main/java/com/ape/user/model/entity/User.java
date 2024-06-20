package com.ape.user.model.entity;

import com.ape.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/14 23:39
 * version :1.0
 **/
@TableName(value = "user")
@Data
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5631032343428460680L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

}
