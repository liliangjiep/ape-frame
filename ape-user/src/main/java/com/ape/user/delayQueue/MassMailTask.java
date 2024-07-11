package com.ape.user.delayQueue;

import lombok.Data;

import java.util.Date;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/11 21:17
 * version :1.0
 **/
@Data
public class MassMailTask {
    // 相关任务ID
    private Long taskId;

    // 延迟任务的开始时间
    private Date startTime;
}
