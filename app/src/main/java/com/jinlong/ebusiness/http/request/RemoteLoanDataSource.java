package com.jinlong.ebusiness.http.request;

import android.support.annotation.Nullable;

import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.http.AppRetrofit;
import com.jinlong.ebusiness.http.HttpUrl;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/9/20
 */
public class RemoteLoanDataSource implements LoanDataSource {

    @Nullable
    private static RemoteLoanDataSource INSTANCE;

    private RemoteLoanDataSource() {

    }

    public static RemoteLoanDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new RemoteLoanDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<Map<String, Object>> login(String email, String pwd) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("email", email);
        params.put("pwd", pwd);
        return new AppRetrofit(false).getFaceIDService().login(HttpUrl.LOGIN_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> register(String email, String pwd) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("email", email);
        params.put("pwd", pwd);
        return new AppRetrofit().getFaceIDService().register(HttpUrl.REGISTER_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> logout(final BaseFragment fragment) {
        return new AppRetrofit().getFaceIDService().logout(HttpUrl.LOGOUT_URL);
    }

    @Override
    public Observable<Map<String, Object>> modifyPwd(String opwd, String npwd) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("opwd", opwd);
        params.put("npwd", npwd);
        return new AppRetrofit().getFaceIDService().modifyPwd(HttpUrl.MODIFY_PWD_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> forgetPwd(String email) {
        return new AppRetrofit(false).getFaceIDService().forgetPwd(HttpUrl.FORGET_PWD_URL, email);
    }

    @Override
    public Observable<Map<String, Object>> bindPhone(String userId, String phone) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("email", userId);
        params.put("pwd", phone);
        return new AppRetrofit().getFaceIDService().bindPhone(HttpUrl.BIND_PHONE_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> bindEmail(String email, String type, String openId, String pwd) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("email", email);
        params.put("type", type);
        params.put("openId", openId);
        params.put("pwd", pwd);
        return new AppRetrofit().getFaceIDService().bindEmail(HttpUrl.BIND_EMAIL_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> getConsigneeAddressList() {
        return new AppRetrofit().getFaceIDService().getConsigneeAddressList(HttpUrl.CONSIGNEE_ADDRESS_URL);
    }

    @Override
    public Observable<Map<String, Object>> getCity() {
        return new AppRetrofit().getFaceIDService().getCity(HttpUrl.CITY_URL);
    }

    @Override
    public Observable<Map<String, Object>> deleteConsigneeAddressByIds(String ids) {
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("ids", ids);
        return new AppRetrofit().getFaceIDService().deleteConsigneeAddressByIds(HttpUrl.CONSIGNEE_ADDRESS_DELETE_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> addConsigneeAddress(String name, String phone, String provinceId, String address, int isDefault) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("name", name);
        params.put("phone", phone);
        params.put("provinceId", provinceId);
        params.put("address", address);
        params.put("isDefault", isDefault);
        return new AppRetrofit().getFaceIDService().addConsigneeAddress(HttpUrl.CONSIGNEE_ADDRESS_ADD_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> editConsigneeAddress(int id, String name, String phone, String provinceId, String address, int isDefault) {
        HashMap<String, Object> params = new HashMap<>(6);
        params.put("id", id);
        params.put("name", name);
        params.put("phone", phone);
        params.put("provinceId", provinceId);
        params.put("address", address);
        params.put("isDefault", isDefault);
        return new AppRetrofit().getFaceIDService().editConsigneeAddress(HttpUrl.CONSIGNEE_ADDRESS_EDIT_URL, params);
    }

    @Override
    public Observable<BaseResponse<MessageListBean>> getMessageList(int type, int page, int rows) {
        return new AppRetrofit().getFaceIDService().getMessageList(HttpUrl.MESSAGE_LIST_URL, type, page, rows);
    }

    @Override
    public Observable<Map<String, Object>> readMessageByIds(String ids) {
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("ids", ids);
        return new AppRetrofit().getFaceIDService().readMessageByIds(HttpUrl.MESSAGE_READ_URL, params);
    }

    @Override
    public Observable<Map<String, Object>> deleteMessageByIds(String ids) {
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("ids", ids);
        return new AppRetrofit().getFaceIDService().deleteMessageByIds(HttpUrl.MESSAGE_DELETE_URL, params);
    }
}