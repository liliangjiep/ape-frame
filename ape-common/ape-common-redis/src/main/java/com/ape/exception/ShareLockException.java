package com.ape.exception;

/**
 * @Author : 李良杰
 * @Description :rendis运行时异常
 * @Date : 2024/7/11 15:33
 * version :1.0
 **/
public class ShareLockException extends RuntimeException {

    public ShareLockException(String message) {
        super(message);
    }

}
