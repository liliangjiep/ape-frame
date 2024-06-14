package com.ape.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class User implements Serializable {
    private static final long serialVersionUID = 5631032343428460680L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer version;
}
