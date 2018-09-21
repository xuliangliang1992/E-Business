package com.jinlong.ebusiness.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jinlong.ebusiness.base.BaseFragment;

import java.util.List;

/**
 * @author xll
 * @date 2018/9/21
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mFragmentList;
    private String[] titles;

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments,String[] titles) {
        super(fm);
        this.mFragmentList = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        if (mFragmentList != null) {
            return mFragmentList.size();
        }
        return -1;
    }
}

