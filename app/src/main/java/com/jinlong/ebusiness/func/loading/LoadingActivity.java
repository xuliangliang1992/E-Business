package com.jinlong.ebusiness.func.loading;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.xll.mvplib.utils.ActivityUtils;

/**
 * @author xll
 * @date 2018/9/15
 */

public class LoadingActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        Logger.d("是你吗 ");
        LoadingFragment loadingFragment = (LoadingFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == loadingFragment) {
            loadingFragment = LoadingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loadingFragment, R.id.fl_content);
        }


    }
}
