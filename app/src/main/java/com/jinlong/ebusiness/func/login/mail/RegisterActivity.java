package com.jinlong.ebusiness.func.login.mail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
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
        setTitle("注册");

        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
