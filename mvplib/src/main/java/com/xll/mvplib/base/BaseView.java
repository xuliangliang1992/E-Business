package com.xll.mvplib.base;

import com.xll.mvplib.subscriber.HttpObserver;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/2
 * 邮箱：liangliang.xu1110@gmail.com
 */

public interface BaseView<T> extends HttpObserver {

    void setPresenter(T presenter);

}
