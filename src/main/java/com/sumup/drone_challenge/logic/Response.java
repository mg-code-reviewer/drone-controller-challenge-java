package com.sumup.drone_challenge.logic;

import java.util.List;

public class Response<T> {

    private T data;

    public Response(T data) {
        this.data = data;
    }

    public Response() {

    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
