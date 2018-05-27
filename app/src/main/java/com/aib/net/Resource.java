package com.aib.net;

public class Resource<T> {
    public String msg;
    public Status status;
    public T data;

    public Resource(String msg, Status status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public static <T> Resource loading(String msg, T data) {
        return new Resource<T>(msg, Status.LOADING, data);
    }

    public static <T> Resource success(String msg, T data) {
        return new Resource<T>(msg, Status.SUCCESS, data);
    }

    public static <T> Resource error(String msg, T data) {
        return new Resource<T>(msg, Status.ERROR, data);
    }
}
