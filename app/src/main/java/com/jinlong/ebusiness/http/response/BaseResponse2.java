package com.jinlong.ebusiness.http.response;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/15
 */
public class BaseResponse2 {

    private String msg;

    private int code;

    private Map<String, Object> data;

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
