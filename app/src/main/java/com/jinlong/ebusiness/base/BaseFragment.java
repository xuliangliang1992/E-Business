package com.jinlong.ebusiness.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.dialog.DialogManager;
import com.jinlong.ebusiness.func.login.LoginActivity;
import com.jinlong.ebusiness.func.main.MainActivity;
import com.xll.mvplib.subscriber.HttpObserver;

/**
 * @author xll
 * @date 2018/9/15
 */

public abstract class BaseFragment extends Fragment implements HttpObserver {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 页面跳转公共方法
     *
     * @param clazz 需要跳转的页面
     */
    public void routeTo(Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }

    public void toLoginActivity() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void toMainActivity() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void showProgressDialog() {
        DialogManager.getInstance().showProgressHUD(getActivity());
    }

    public void dismissProgressDialog() {
        DialogManager.getInstance().dismissProgressHUD();
    }

    @Override
    public void httpUnauthorized() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        startActivityForResult(intent, Constant.REQUEST_LOGIN);
    }

    @Override
    public void httpException(int code) {

    }

    @Override
    public void httpTimeOutException() {

    }

    @Override
    public void httpOtherException(String message) {

    }

}
