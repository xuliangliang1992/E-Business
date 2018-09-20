package com.jinlong.ebusiness.http.request;

import com.jinlong.ebusiness.base.BaseFragment;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/9/20
 */
public interface LoanDataSource {
    /**
     * 登录
     *
     * @param email 登录用户邮箱地址
     * @param pwd   密码
     * @return
     */
    Observable<Map<String, Object>> login(String email, String pwd);

    /**
     * 注册
     *
     * @param email 邮箱
     * @param pwd   密码
     * @return
     */
    Observable<Map<String, Object>> register(String email, String pwd);

    /**
     * 登出
     *
     * @return
     */
    Observable<Map<String, Object>> logout(BaseFragment fragment);

    /**
     * 修改密码
     *
     * @param opwd
     * @param npwd
     * @return
     */
    Observable<Map<String, Object>> modifyPwd(String opwd, String npwd);

    /**
     * 忘记密码
     *
     * @param email
     * @return
     */
    Observable<Map<String, Object>> forgetPwd(String email);

    /**
     * 绑定手机号
     *
     * @param userId 用户ID
     * @param phone  手机号
     * @return
     */
    Observable<Map<String, Object>> bindPhone(String userId, String phone);

    /**
     * 绑定邮箱
     *
     * @param email  要绑定的邮箱
     * @param type   类型，0：Gmail，1：Facebook
     * @param openId 三方登录OpenID
     * @param pwd    登录密码
     * @return
     */
    Observable<Map<String, Object>> bindEmail(String email, String type, String openId, String pwd);
}
