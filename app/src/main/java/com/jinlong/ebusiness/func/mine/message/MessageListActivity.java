package com.jinlong.ebusiness.func.mine.message;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.OnClick;

/**
 * @author xll
 * @date 2018/9/17
 */

public class MessageListActivity extends BaseActivity {

    private MessageListFragment fragment;

    boolean isSysDone = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.message_list);
        setRightText(getString(R.string.manage));

        fragment = (MessageListFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = MessageListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }


    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }

    @OnClick(R.id.tv_right)
    public void manage() {
        if (fragment.getCurrPosition() == 0) {
            fragment.getSystemMessageFragment().manageMessage(isSysDone);
            isSysDone = !isSysDone;
            setRightText(isSysDone ? "完成" : getString(R.string.manage));
        } else if (fragment.getCurrPosition() == 1) {

        }
    }
}
