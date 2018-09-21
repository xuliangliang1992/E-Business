package com.jinlong.ebusiness.http.request;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.func.login.LoginActivity;
import com.jinlong.ebusiness.http.AppRetrofit;
import com.jinlong.ebusiness.http.HttpFilterFunc;
import com.jinlong.ebusiness.http.HttpUrl;
import com.xll.mvplib.subscriber.FilterHandlerListener;
import com.xll.mvplib.utils.ToastUtil;

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
        return new AppRetrofit(false).getFaceIDService().login(HttpUrl.LOGIN_URL, params)
                .filter(new HttpFilterFunc());
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
        return new AppRetrofit().getFaceIDService().logout(HttpUrl.LOGOUT_URL)
                .filter(new HttpFilterFunc(new FilterHandlerListener() {
                    @Override
                    public void handleFilter(int code, final String msg) {
                        switch (code) {
                            case Constant.UNAUTHORIZED:
                                Intent intent = new Intent();
                                intent.setClass(fragment.getActivity(), LoginActivity.class);
                                fragment.startActivityForResult(intent, Constant.REQUEST_LOGIN);
                                break;
                            default:
                                ToastUtil.showToast(fragment.getActivity(), msg);
                                break;
                        }
                    }
                }));
    }

    @Override
    public Observable<Map<String, Object>> modifyPwd(String opwd, String npwd) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("opwd", opwd);
        params.put("npwd", npwd);
        return new AppRetrofit().getFaceIDService().modifyPwd(HttpUrl.MODIFY_PWD_URL, params)
                .filter(new HttpFilterFunc());
    }

    @Override
    public Observable<Map<String, Object>> forgetPwd(String email) {
        return new AppRetrofit(false).getFaceIDService().forgetPwd(HttpUrl.FORGET_PWD_URL, email)
                .filter(new HttpFilterFunc());
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
}