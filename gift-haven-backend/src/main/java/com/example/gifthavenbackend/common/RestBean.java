package com.example.gifthavenbackend.common;

import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    private T message;

    private RestBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> RestBean<T> sueecss() {
        return new RestBean<T>(200, true, null);
    }

    public static <T> RestBean<T> sueecss(T data) {
        return new RestBean<T>(200, true, data);
    }

    public static <T> RestBean<T> failure(int status) {
        return new RestBean<T>(200, false, null);

    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<T>(200, false, data);

    }
}
