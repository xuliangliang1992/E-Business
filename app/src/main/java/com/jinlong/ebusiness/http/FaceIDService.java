package com.jinlong.ebusiness.http;

import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 接口
 *
 * @author xll
 * @date 2018/9/20
 */

public interface FaceIDService {

    /**
     * 登录
     *
     * @param url
     * @param map account pwd
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> login(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 注册
     *
     * @param url
     * @param map email pwd
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> register(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 登出
     *
     * @param url
     * @return
     */
    @POST
    Observable<Map<String, Object>> logout(@Url String url);

    /**
     * 修改密码
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> modifyPwd(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 忘记密码
     *
     * @param url
     * @param email
     * @return
     */
    @GET
    Observable<Map<String, Object>> forgetPwd(@Url String url, @Query("email") String email);

    /**
     * 绑定手机号
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> bindPhone(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 绑定邮箱
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> bindEmail(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 收货地址
     *
     * @param url
     * @return
     */
    @GET
    Observable<Map<String, Object>> getConsigneeAddressList(@Url String url);

    /**
     * 省市区
     *
     * @param url
     * @return
     */
    @GET
    Observable<Map<String, Object>> getCity(@Url String url);

    /**
     * 删除收货地址
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> deleteConsigneeAddressByIds(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 编辑收货地址
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> editConsigneeAddress(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 添加收货地址
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> addConsigneeAddress(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 消息列表
     *
     * @param url
     * @param type
     * @param page
     * @param rows
     * @return
     */
    @GET
    Observable<BaseResponse<MessageListBean>> getMessageList(@Url String url, @Query("type") int type, @Query("page") int page, @Query("rows") int rows);

    /**
     * 消息已读
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> readMessageByIds(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 删除消息
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> deleteMessageByIds(@Url String url, @FieldMap Map<String, Object> map);

}

