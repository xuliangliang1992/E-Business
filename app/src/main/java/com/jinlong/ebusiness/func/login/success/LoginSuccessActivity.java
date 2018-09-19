package com.jinlong.ebusiness.func.login.success;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author xll
 * @date 2018/9/19
 */

public class LoginSuccessActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登录成功");
        setRightText("跳过");
        LoginSuccessFragment fragment = (LoginSuccessFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = LoginSuccessFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }

}
