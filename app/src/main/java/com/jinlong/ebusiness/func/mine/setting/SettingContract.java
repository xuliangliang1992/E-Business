package com.jinlong.ebusiness.func.mine.setting;

import com.jinlong.ebusiness.base.BaseFragment;
import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/20
 */

public class SettingContract {

    interface View extends BaseView<Presenter> {

        /**
         * 登出成功
         *
         */
        void logoutSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 登出
         */
        void logout(BaseFragment fragment);

    }
}
