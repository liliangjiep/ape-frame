package com.example.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/18 23:24
 * version :1.0
 **/
    @Data
    public class Result<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    private Result() {
    }

    public static Result ok() {
        return ok(null);
    }

    public static <T> Result ok(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error() {
        return error(null);
    }

    public static <T> Result error(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.ERROR);
        result.setMessage(ResultMessage.ERROR);
        result.setData(data);
        return result;
    }

    public static Result other(Integer resultCode, String resultMessage) {
        return other(resultCode, resultMessage, null);
    }

    public static <T> Result other(Integer resultCode, String resultMessage, T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setData(data);
        return result;
    }
}
