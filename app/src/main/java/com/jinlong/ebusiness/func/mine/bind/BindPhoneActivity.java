package com.jinlong.ebusiness.func.mine.bind;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.jinlong.ebusiness.func.mine.password.ModifyPasswordPresenter;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @aucthor xll
 * @date 2018/9/22
 */

public class BindPhoneActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("绑定手机号");

        BindPhoneFragment fragment = (BindPhoneFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = BindPhoneFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }
        new BindPhonePresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());
    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
