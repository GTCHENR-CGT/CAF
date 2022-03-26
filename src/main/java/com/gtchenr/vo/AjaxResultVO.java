package com.gtchenr.vo;

import java.util.List;

public class AjaxResultVO<T> {

    private int statusCode;
    private String msg;
    private T data;

    public AjaxResultVO() {
        this.statusCode = 200;
        this.msg = "success";
        this.data = null;
    }

    public AjaxResultVO(int statusCode, String msg, T data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatueCode(int statueCode) {
        this.statusCode = statueCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
