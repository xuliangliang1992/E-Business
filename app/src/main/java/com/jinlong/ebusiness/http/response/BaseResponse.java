package com.jinlong.ebusiness.http.response;

/**
 * @author xll
 * @date 2018/9/15
 */
public class BaseResponse<T> {

    private String msg;

    private int code;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
