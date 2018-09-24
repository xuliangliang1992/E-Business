package com.jinlong.ebusiness.http.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author xll
 * @date 2018/9/20
 */
public class LoanRepository implements LoanDataSource {

    @Nullable
    private static LoanRepository INSTANCE;

    private RemoteLoanDataSource mRemoteLoanDataSource;

    private LoanRepository(RemoteLoanDataSource remoteLoanDataSource) {
        this.mRemoteLoanDataSource = remoteLoanDataSource;
    }

    public static LoanRepository getInstance(@NonNull RemoteLoanDataSource remoteLoanDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new LoanRepository(remoteLoanDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<Map<String, Object>> login(String email, String pwd) {
        return mRemoteLoanDataSource.login(email, pwd);
    }

    @Override
    public Observable<Map<String, Object>> register(String email, String pwd) {
        return mRemoteLoanDataSource.register(email, pwd);
    }

    @Override
    public Observable<Map<String, Object>> logout(BaseFragment fragment) {
        return mRemoteLoanDataSource.logout(fragment);
    }

    @Override
    public Observable<Map<String, Object>> modifyPwd(String opwd, String npwd) {
        return mRemoteLoanDataSource.modifyPwd(opwd, npwd);
    }

    @Override
    public Observable<Map<String, Object>> forgetPwd(String email) {
        return mRemoteLoanDataSource.forgetPwd(email);
    }

    @Override
    public Observable<Map<String, Object>> bindPhone(String userId, String phone) {
        return mRemoteLoanDataSource.bindPhone(userId, phone);
    }

    @Override
    public Observable<Map<String, Object>> bindEmail(String email, String type, String openId, String pwd) {
        return mRemoteLoanDataSource.bindEmail(email, type, openId, pwd);
    }

    @Override
    public Observable<Map<String, Object>> getConsigneeAddressList() {
        return mRemoteLoanDataSource.getConsigneeAddressList();
    }

    @Override
    public Observable<Map<String, Object>> getCity() {
        return mRemoteLoanDataSource.getCity();
    }

    @Override
    public Observable<Map<String, Object>> deleteConsigneeAddressByIds(String ids) {
        return mRemoteLoanDataSource.deleteConsigneeAddressByIds(ids);
    }

    @Override
    public Observable<Map<String, Object>> addConsigneeAddress(String name, String phone, String provinceId, String address, int isDefault) {
        return mRemoteLoanDataSource.addConsigneeAddress(name, phone, provinceId, address, isDefault);
    }

    @Override
    public Observable<Map<String, Object>> editConsigneeAddress(int id, String name, String phone, String provinceId, String address, int isDefault) {
        return mRemoteLoanDataSource.editConsigneeAddress(id, name, phone, provinceId, address, isDefault);
    }

    @Override
    public Observable<BaseResponse<MessageListBean>> getMessageList(int type, int page, int rows) {
        return mRemoteLoanDataSource.getMessageList(type, page, rows);
    }

    @Override
    public Observable<Map<String, Object>> readMessageByIds(String ids) {
        return mRemoteLoanDataSource.readMessageByIds(ids);
    }

    @Override
    public Observable<Map<String, Object>> deleteMessageByIds(String ids) {
        return mRemoteLoanDataSource.deleteMessageByIds(ids);
    }
}
