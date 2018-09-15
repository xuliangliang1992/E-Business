package com.xll.mvplib.base;

/**
 * 功能：presenter 基类
 * 作者：xll
 * 日期：2018/1/2
 * 邮箱：liangliang.xu1110@gmail.com
 */

public interface BasePresenter {
    /**
     * 订阅
     */
    void subscriber();

    /**
     * 取消订阅
     */
    void unSubscriber();
}
