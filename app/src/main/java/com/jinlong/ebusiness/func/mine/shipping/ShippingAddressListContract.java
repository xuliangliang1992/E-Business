package com.jinlong.ebusiness.func.mine.shipping;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @aucthor xll
 * @date 2018/9/23
 */
public class ShippingAddressListContract {
    interface View extends BaseView<Presenter> {

        /**
         * 收货地址请求成功
         *
         * @param map
         */
        void getConsigneeAddressListRequestSuccess(Map<String, Object> map);

        /**
         * 删除收获地址请求成功
         *
         * @param map
         */
        void deleteConsigneeAddressByIdsRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 收货地址
         */
        void getConsigneeAddressList();

        /**
         * 删除收获地址
         *
         * @param ids
         */
        void deleteConsigneeAddressByIds(String ids);

    }
}
