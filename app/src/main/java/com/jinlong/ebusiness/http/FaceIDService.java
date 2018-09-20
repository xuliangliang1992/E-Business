package com.jinlong.ebusiness.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
    Observable<Map<String,Object>> logout(@Url String url);

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
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> forgetPwd(@Url String url, @FieldMap Map<String, Object> map);

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
}

