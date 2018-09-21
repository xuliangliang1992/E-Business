package com.jinlong.ebusiness.func.mine.setting;

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

public class SettingActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.mine);

        SettingFragment fragment = (SettingFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = SettingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }    new SettingPresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());

    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
