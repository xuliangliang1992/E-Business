package com.xll.mvplib.subscriber;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/2
 * 邮箱：liangliang.xu1110@gmail.com
 */

public interface HttpObserver {

    void httpUnauthorized();

    void httpException(int code);

    void httpTimeOutException();

    void httpOtherException(String message);
}
