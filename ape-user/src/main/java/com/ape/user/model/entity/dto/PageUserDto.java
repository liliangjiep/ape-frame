package com.ape.user.model.entity.dto;

import com.ape.page.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 23:05
 * version :1.0
 **/
@Data
public class PageUserDto extends PageRequest implements Serializable {

    private static final long serialVersionUID = 8850946145794429351L;
    private String name;

    private Integer age;
}
