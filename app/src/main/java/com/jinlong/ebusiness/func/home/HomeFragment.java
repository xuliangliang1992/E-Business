package com.jinlong.ebusiness.func.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/15
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.rv_home)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private ArrayList<HashMap<String, Object>> listItem;
    MyAdapter searchAdapterSingleLayout, Adapter_linearLayout, Adapter_GridLayout, Adapter_FixLayout, Adapter_ScrollFixLayout, Adapter_FloatLayout, Adapter_ColumnLayout, bannerAdapterSingleLayout, newAdapterSingleLayout, hotProductAdapterSingleLayout, hotSaleAdapterSingleLayout, Adapter_onePlusNLayout,
            Adapter_StickyLayout, Adapter_StaggeredGridLayout;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView

        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);


        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);

        }

        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(10);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔

        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        Adapter_linearLayout = new MyAdapter(getActivity(), linearLayoutHelper, 10, listItem, 2);
        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
        //        stickyLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //        stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部
        //
        //        stickyLayoutHelper.setOffset(50);// 设置吸边位置的偏移量

        Adapter_StickyLayout = new MyAdapter(getActivity(), stickyLayoutHelper, 1, listItem, 1) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("类别筛选");
                holder.mTvRight.setText("建材/家具 ");
            }
        };

        SingleLayoutHelper newSingleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        //        singleLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        //

        newAdapterSingleLayout = new MyAdapter(getActivity(), newSingleLayoutHelper, 1, listItem, MyAdapter.ONE_ITEM) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("最新上架");
                holder.mTvRight.setText("查看全部");
            }
        };
        SingleLayoutHelper bannerSingleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        bannerSingleLayoutHelper.setItemCount(3);// 设置布局里Item个数
        bannerSingleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        bannerSingleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        bannerSingleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        bannerSingleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        //

        bannerAdapterSingleLayout = new MyAdapter(getActivity(), bannerSingleLayoutHelper, 1, listItem, MyAdapter.ONE_ITEM) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("banner");
            }
        };
        SingleLayoutHelper hotProductSingleLayoutHelper = new SingleLayoutHelper();

        hotProductAdapterSingleLayout = new MyAdapter(getActivity(), hotProductSingleLayoutHelper, 1, listItem, MyAdapter.ONE_ITEM) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("热门商品");
                holder.mTvRight.setText("查看全部");
            }
        };
        SingleLayoutHelper hotSaleSingleLayoutHelper = new SingleLayoutHelper();

        hotSaleAdapterSingleLayout = new MyAdapter(getActivity(), hotSaleSingleLayoutHelper, 1, listItem, MyAdapter.ONE_ITEM) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("热卖专场");
            }
        };
        SingleLayoutHelper searchSingleLayout = new SingleLayoutHelper();

        searchAdapterSingleLayout = new MyAdapter(getActivity(), searchSingleLayout, 1, listItem, MyAdapter.ONE_ITEM) {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
                holder.mTvLeft.setText("搜索商品名或者店铺名");
                holder.mTvLeft.setGravity(Gravity.CENTER);
                holder.mTvRight.setVisibility(View.GONE);
            }
        };
        /**
         *步骤5:将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
         **/

        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        adapters.add(searchAdapterSingleLayout);
        adapters.add(bannerAdapterSingleLayout);
        adapters.add(hotProductAdapterSingleLayout);
        adapters.add(Adapter_linearLayout);
        adapters.add(newAdapterSingleLayout);
        adapters.add(Adapter_linearLayout);
        adapters.add(hotSaleAdapterSingleLayout);
        adapters.add(Adapter_StickyLayout);
        adapters.add(Adapter_linearLayout);
        //        adapters.add(Adapter_ScrollFixLayout) ;
        //        adapters.add(Adapter_GridLayout) ;
        //        adapters.add(Adapter_FixLayout) ;
        //        adapters.add(Adapter_FloatLayout) ;
        //        adapters.add(Adapter_ColumnLayout) ;
        //        adapters.add(Adapter_onePlusNLayout) ;
        //        adapters.add(Adapter_StaggeredGridLayout) ;


        //
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(delegateAdapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
