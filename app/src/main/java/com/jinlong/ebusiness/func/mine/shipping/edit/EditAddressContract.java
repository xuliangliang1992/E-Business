package com.jinlong.ebusiness.func.mine.shipping.edit;

import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @aucthor xll
 * @date 2018/9/23
 */
public class EditAddressContract {
    interface View extends BaseView<Presenter> {

        /**
         * 收货地址请求成功
         *
         * @param map
         */
        void editConsigneeAddressRequestSuccess(Map<String, Object> map);

        /**
         * 删除收获地址请求成功
         *
         * @param map
         */
        void addConsigneeAddressRequestSuccess(Map<String, Object> map);

        /**
         * 省市区
         */
        void getCityRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 编辑收货地址
         *
         * @param name
         * @param phone
         * @param provinceId
         * @param address
         * @param isDefault
         * @return
         */
        void addConsigneeAddress(String name, String phone, String provinceId, String address, int isDefault);

        /**
         * 编辑收货地址
         *
         * @param id
         * @param name
         * @param phone
         * @param provinceId
         * @param address
         * @param isDefault
         */
        void editConsigneeAddress(int id, String name, String phone, String provinceId, String address, int isDefault);

        /**
         * 省市区
         */
        void getCity();
    }
}
