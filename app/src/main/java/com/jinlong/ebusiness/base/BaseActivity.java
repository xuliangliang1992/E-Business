package com.jinlong.ebusiness.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinlong.ebusiness.R;

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
    }
}
