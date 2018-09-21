package com.jinlong.ebusiness.func.mine.password;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ModifyPasswordContract {
    interface View extends BaseView<Presenter> {

        /**
         * 修改密码请求成功
         *
         * @param map
         */
        void modifyPwdRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 修改密码
         *
         * @param opwd
         * @param npwd
         */
        void modifyPwd(String opwd, String npwd);

    }
}
