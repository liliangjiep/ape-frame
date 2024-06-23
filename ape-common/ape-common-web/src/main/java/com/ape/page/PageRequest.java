package com.ape.page;

import lombok.Data;
import lombok.Setter;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/23 19:56
 * version :1.0
 **/
@Setter
public class PageRequest {
    private Long pageNo = 1L;

    private Long pageSize = 10L;

    public Long getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1L;
        }
        return pageNo;
    }

    public Long getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 10L;
        }
        return pageSize;
    }
}
