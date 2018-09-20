package com.jinlong.ebusiness.base;

import com.xll.mvplib.base.APP;

/**
 * @author xll
 * @date 2018/9/20
 */
public class MainApplication extends APP {

    private static MainApplication instance;
    private static boolean isLogin;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static boolean isIsLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        MainApplication.isLogin = isLogin;
    }
}
