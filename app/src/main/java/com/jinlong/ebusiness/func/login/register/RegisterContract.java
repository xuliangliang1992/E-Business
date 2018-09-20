package com.jinlong.ebusiness.func.login.register;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/20
 */

public class RegisterContract {

    interface View extends BaseView<Presenter> {

        /**
         * 登录成功
         *
         * @param map
         */
        void registerSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 登录
         *
         * @param email
         * @param pwd
         */
        void register(String email, String pwd);

    }
}
