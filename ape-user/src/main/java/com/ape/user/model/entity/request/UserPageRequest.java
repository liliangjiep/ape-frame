package com.ape.user.model.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/20 23:30
 * version :1.0
 **/
@Data
public class UserPageRequest implements Serializable {
    private static final long serialVersionUID = 5373210498960485185L;
    private String name;

    private Integer age;

    /**
     * 分页页数(应该是pageNo)
     */
    private Integer pageIndex;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
