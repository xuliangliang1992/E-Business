package com.jinlong.ebusiness.func.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.OnClick;

/**
 * @author xll
 * @date 2018/9/17
 */

public class LoginActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.login));
        setRightImageView(R.drawable.close);
        hideIvBack();
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        new LoginPresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());
    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }

    @OnClick(R.id.ll_right)
    public void close() {
        finish();
    }
}
