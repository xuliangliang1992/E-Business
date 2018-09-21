package com.jinlong.ebusiness.func.login.password;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ForgetPasswordActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.forget_pwd));

        ForgetPasswordFragment fragment = (ForgetPasswordFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = ForgetPasswordFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        new ForgetPasswordPresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());

    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
