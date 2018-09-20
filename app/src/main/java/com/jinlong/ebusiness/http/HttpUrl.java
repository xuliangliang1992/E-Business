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
    public static final String LOGOUT_URL = Constant.BASE_URL + "Admin/logout";
    /**
     * 修改密码
     */
    public static final String MODIFY_PWD_URL = Constant.BASE_URL + "Api/User/passwd";
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
    public static final String ADDRESS_URL = Constant.BASE_URL + "Api/Consignee/list";
}
