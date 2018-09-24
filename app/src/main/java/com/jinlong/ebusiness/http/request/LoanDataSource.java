package com.jinlong.ebusiness.http.request;

import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;

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

    /**
     * 收货地址
     */
    Observable<Map<String, Object>> getConsigneeAddressList();

    /**
     * 省市区
     * @return
     */
    Observable<Map<String, Object>> getCity();

    /**
     * 删除收货地址
     *
     * @param ids
     * @return
     */
    Observable<Map<String, Object>> deleteConsigneeAddressByIds(String ids);

    /**
     * 添加收货地址
     *
     * @param name
     * @param phone
     * @param provinceId
     * @param address
     * @param isDefault
     * @return
     */
    Observable<Map<String, Object>> addConsigneeAddress(String name, String phone, String provinceId, String address, int isDefault);

    /**
     * 编辑收货地址
     *
     * @param id
     * @param name
     * @param phone
     * @param provinceId
     * @param address
     * @param isDefault
     * @return
     */
    Observable<Map<String, Object>> editConsigneeAddress(int id,String name, String phone, String provinceId, String address, int isDefault);

    /**
     * 消息列表
     *
     * @param type 类型：1系统消息/2通知
     * @param page 页数,不填默认1
     * @param rows 每页条数,不填默认10
     * @return
     */
    Observable<BaseResponse<MessageListBean>> getMessageList(int type, int page, int rows);

    /**
     * 消息已读
     *
     * @param ids
     * @return
     */
    Observable<Map<String, Object>> readMessageByIds(String ids);

    /**
     * 删除消息
     *
     * @param ids
     * @return
     */
    Observable<Map<String, Object>> deleteMessageByIds(String ids);

}
