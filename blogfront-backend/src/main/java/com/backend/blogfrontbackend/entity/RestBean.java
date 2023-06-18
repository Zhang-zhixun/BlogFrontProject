package com.backend.blogfrontbackend.entity;

import lombok.Data;


//返回json格式 状态 是否成功 信息
@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    private T message;


    public RestBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> RestBean<T> success() {
        return new RestBean<>(200, true, null);
    }

    //登录成功
    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, true, data);
    }

    //失败
    public static <T> RestBean<T> failure(int status) {
        return new RestBean<>(status, false, null);
    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<>(status, false, data);
    }

}
