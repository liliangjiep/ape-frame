package com.ape.user.designPattern.prod;

import com.ape.bean.Result;
import com.ape.bean.ResultCode;

/**
 * @Author : 李良杰
 * @Description :api模板
 * @Date : 2024/7/15 22:20
 * version :1.0
 **/
public class ApiTemplate {
    public void execute(Result result, final Action action) {
        try {
            action.validate();
            action.execute();
            action.after();
            result.setSuccess(true);
            result.setCode(1024);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setCode(ResultCode.ERROR);
        }
    }
}
