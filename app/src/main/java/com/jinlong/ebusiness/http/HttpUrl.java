package com.jinlong.ebusiness.http;

import com.jinlong.ebusiness.constant.Constant;

/**
 * @author xll
 * @date 2018/9/20
 */

public class HttpUrl {

    /**
     * 登录
     */
    public static final String LOGIN_URL = Constant.BASE_URL + "Api/User/login";
    /**
     * 注册
     */
    public static final String REGISTER_URL = Constant.BASE_URL + "Api/User/register";
    /**
     * 登出
     */
    public static final String LOGOUT_URL = Constant.BASE_URL + "Api/User/logout";
    /**
     * 修改密码
     */
    public static final String MODIFY_PWD_URL = Constant.BASE_URL + "Api/User/password";
    /**
     * 忘记密码
     */
    public static final String FORGET_PWD_URL = Constant.BASE_URL + "Api/User/forget";
    /**
     * 绑定手机号
     */
    public static final String BIND_PHONE_URL = Constant.BASE_URL + "Api/User/bindPhone";
    /**
     * 绑定邮箱
     */
    public static final String BIND_EMAIL_URL = Constant.BASE_URL + "Api/User/bindEmail";
    /**
     * 收货地址
     */
    public static final String CONSIGNEE_ADDRESS_URL = Constant.BASE_URL + "Api/Consignee/list";

    /**
     * 省市区
     */
    public static final String CITY_URL = Constant.BASE_URL + "Common/territory/list";
    /**
     * 删除收获地址
     */
    public static final String CONSIGNEE_ADDRESS_DELETE_URL = Constant.BASE_URL + "Api/Consignee/deleteByIds";
    /**
     * 新增收获地址
     */
    public static final String CONSIGNEE_ADDRESS_ADD_URL = Constant.BASE_URL + "Api/Consignee/add";
    /**
     * 编辑收获地址
     */
    public static final String CONSIGNEE_ADDRESS_EDIT_URL = Constant.BASE_URL + "Api/Consignee/edit";

    /**
     * 我的收藏列表
     */
    public static final String COLLECT_URL = Constant.BASE_URL + "Api/Collect/list";
    /**
     * 我的收藏删除
     */
    public static final String COLLECT_DELETE_URL = Constant.BASE_URL + "/Api/Collect/delete";
    /**
     * 我的收藏-添加
     */
    public static final String COLLECT_ADD_URL = Constant.BASE_URL + "/Api/Collect/add";


    /**
     * 购物车-列表
     */
    public static final String CART_URL = Constant.BASE_URL + "Api/Cart/list";


    /**
     * 购物车-商品删除
     */
    public static final String CART_DELETE_URL = Constant.BASE_URL + "/Api/Cart/delete";


    /**
     * 购物车-商品数量变更
     */
    public static final String CART_EDIT_URL = Constant.BASE_URL + "/Api/Cart/edit";


    /**
     * 购物车-添加
     */
    public static final String CART_ADD_URL = Constant.BASE_URL + "/Api/Cart/add";


    /**
     * 下单
     */
    public static final String ORDER_ADD_URL = Constant.BASE_URL + "/Api/Order/add";


    /**
     * 订单列表
     */
    public static final String ORDER_LIST_URL = Constant.BASE_URL + "/Api/Order/list";


    /**
     * 订单详情
     */
    public static final String ORDER_DETAIL_URL = Constant.BASE_URL + "/Api/Order/detail";


    /**
     * 消息-删除
     */
    public static final String MESSAGE_DELETE_URL = Constant.BASE_URL + "Api/Message/delete";


    /**
     * 消息-已读
     */
    public static final String MESSAGE_READ_URL = Constant.BASE_URL + "Api/Message/read";


    /**
     * 消息-详情
     */
    public static final String MESSAGE_DETAIL_URL = Constant.BASE_URL + "Api/Message/detail";

    /**
     * 系统消息/通知
     */
    public static final String MESSAGE_LIST_URL = Constant.BASE_URL + "Api/Message/list";

    /**
     * FAQ
     */
    public static final String FAQ_URL = Constant.BASE_URL + "Api/Company/faq";

    /**
     * 意见反馈
     */
    public static final String GUEST_BOOK_URL = Constant.BASE_URL + "Api/Company/Guestbook/add";


}
