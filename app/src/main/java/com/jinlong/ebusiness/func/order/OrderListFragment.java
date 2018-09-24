package com.jinlong.ebusiness.func.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.adapter.MainViewPagerAdapter;
import com.jinlong.ebusiness.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/17
 */

public class OrderListFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private String[] titles;
    private List<BaseFragment> fragments;

    public static OrderListFragment newInstance() {
        return new OrderListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initResource();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        MainViewPagerAdapter mAdapter = new MainViewPagerAdapter(getFragmentManager(), fragments,titles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void initResource() {
        fragments = new ArrayList<>();
        titles = new String[]{"进行中", "已完成", "已关闭"};
        fragments.add(CompletedOrderFragment.newInstance());
        fragments.add(OrderInProgressFragment.newInstance());
        fragments.add(ClosedOrderFragment.newInstance());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
