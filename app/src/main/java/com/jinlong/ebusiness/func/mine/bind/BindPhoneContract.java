package com.jinlong.ebusiness.func.mine.bind;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/21
 */

public class BindPhoneContract {
    interface View extends BaseView<Presenter> {

        /**
         * 绑定手机号请求成功
         *
         * @param map
         */
        void bindPhoneRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 绑定手机号
         *
         * @param userId
         * @param phone
         */
        void bindPhone(String userId, String phone);

    }
}
