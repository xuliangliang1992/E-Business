package com.jinlong.ebusiness.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.constant.Constant;
import com.xll.mvplib.utils.SharePreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/15
 */

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        changeAppLanguage();
    }

    public void changeAppLanguage() {
        //这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        String sta = (String) SharePreferenceUtil.getInstance().get(this, Constant.SHARE_PERFERENCE_FILE_NAME, SharePreferenceUtil.LANGUAGE, Constant.CHINESE);
        // 本地语言设置
        Locale myLocale = new Locale(sta);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public void onEvent(String str) {
        switch (str) {
            case Constant.EVENT_REFRESH_LANGUAGE:
                changeAppLanguage();
                recreate();//刷新界面
                break;
            default:
                break;
        }
    }

    protected void setTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 默认的title bar是展示的
     * 如有页面没有title 用该方法隐藏
     * 隐藏title bar
     */
    public void hideTitleBar() {
        mAppBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
