package com.jinlong.ebusiness.func.login.register;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author xll
 * @date 2018/9/17
 */

public class RegisterActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.register));
        setRightText(getString(R.string.jump));

        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        new RegisterPresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());

    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
