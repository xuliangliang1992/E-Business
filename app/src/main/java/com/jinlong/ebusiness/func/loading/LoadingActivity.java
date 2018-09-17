package com.jinlong.ebusiness.func.loading;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author xll
 * @date 2018/9/15
 */

public class LoadingActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();

        LoadingFragment loadingFragment = (LoadingFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == loadingFragment) {
            loadingFragment = LoadingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loadingFragment, R.id.fl_content);
        }

    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
