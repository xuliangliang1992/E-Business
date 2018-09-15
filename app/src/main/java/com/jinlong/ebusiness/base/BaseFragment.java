package com.jinlong.ebusiness.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * @author xll
 * @date 2018/9/15
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 页面跳转公共方法
     *
     * @param clazz 需要跳转的页面
     */
    public void RouteTo(Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }

    public void toLogin(){

    }
}
