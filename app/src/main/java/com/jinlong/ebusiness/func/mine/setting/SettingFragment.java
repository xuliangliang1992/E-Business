package com.jinlong.ebusiness.func.mine.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.base.MainApplication;
import com.jinlong.ebusiness.constant.Constant;
import com.xll.mvplib.utils.SharePreferenceUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/17
 */

public class SettingFragment extends BaseFragment implements SettingContract.View {

    Unbinder unbinder;
    private SettingContract.Presenter mPresenter;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void logoutSuccess() {
        dismissProgressDialog();
        SharePreferenceUtil.getInstance().put(MainApplication.getInstance().getApplicationContext(),
                Constant.SHARED_PREFERENCE_FILE_NAME,SharePreferenceUtil.TOKEN, "");
        toLoginActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        showProgressDialog();
        mPresenter.logout(this);
    }
}
