package com.jinlong.ebusiness.func.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.func.cart.CartFragment;
import com.jinlong.ebusiness.func.category.CategoryFragment;
import com.jinlong.ebusiness.func.home.HomeFragment;
import com.jinlong.ebusiness.func.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/15
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    Unbinder unbinder;
    private int[] imgRes;
    private String[] titles;
    private List<BaseFragment> fragments;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initResource();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        MainPageAdapter mAdapter = new MainPageAdapter(getFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager, true);
        for (int i = 0; i < fragments.size(); i++) {
            mTabLayout.getTabAt(i).setCustomView(getTabView(i));
        }

        return view;
    }

    private void initResource() {
        fragments = new ArrayList<>();
        imgRes = new int[]{R.drawable.selector_main_tab_home, R.drawable.selector_main_tab_category,
                R.drawable.selector_main_tab_cart, R.drawable.selector_main_tab_mine};
        titles = new String[]{getString(R.string.home), getString(R.string.classify), getString(R.string.cart), getString(R.string.mine)};

        fragments.add(HomeFragment.newInstance());
        fragments.add(CategoryFragment.newInstance());
        fragments.add(CartFragment.newInstance());
        fragments.add(MineFragment.newInstance());

    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.main_tab_item, mTabLayout, false);
        ImageView imageView = view.findViewById(R.id.img_title);
        TextView textView = view.findViewById(R.id.txt_title);
        imageView.setImageResource(imgRes[position]);
        textView.setText(titles[position]);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class MainPageAdapter extends FragmentStatePagerAdapter {

        private List<BaseFragment> mFragmentList;

        public MainPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            if (mFragmentList != null) {
                return mFragmentList.size();
            }
            return -1;
        }
    }
}
