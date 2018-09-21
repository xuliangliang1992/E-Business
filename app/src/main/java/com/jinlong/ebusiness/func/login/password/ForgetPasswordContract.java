package com.jinlong.ebusiness.func.login.password;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ForgetPasswordContract {
    interface View extends BaseView<Presenter> {

        /**
         * 忘记密码接口成功
         *
         * @param map
         */
        void forgetPwdRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 忘记密码
         *
         * @param email
         */
        void forgetPwd(String email);

    }
}
