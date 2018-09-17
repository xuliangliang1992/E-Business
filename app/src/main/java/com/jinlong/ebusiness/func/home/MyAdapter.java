package com.jinlong.ebusiness.func.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jinlong.ebusiness.R;
import com.xll.mvplib.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carson_Ho on 17/4/26.
 */
public class MyAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    public static final int ONE_ITEM = 1;
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    private ArrayList<HashMap<String, Object>> listItem;
    // 用于存放数据列表

    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;
    private int layoutType;

    private MyItemClickListener myItemClickListener;
    // 用于设置Item点击事件

    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem, int layoutType) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem, layoutType);
    }

    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem, int layoutType) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;
        this.layoutType = layoutType;

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // 把ViewHolder绑定Item的布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (layoutType) {
            case ONE_ITEM:
                return new LinearItemViewHolder(LayoutInflater.from(context).inflate(R.layout.home_linear_item, parent, false));

            case 2:


            case 3:


            default:
                return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));

        }

    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    // 绑定Item的数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof LinearItemViewHolder) {
            LinearItemViewHolder holder = (LinearItemViewHolder) viewHolder;
            holder.mTvLeft.setText("最新产品");
            holder.mTvRight.setText("查看全部");
        } else {
            MainViewHolder holder = (MainViewHolder) viewHolder;
            holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
            holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(context, "position" + position);
                }
            });
        }


    }

    // 返回Item数目
    @Override
    public int getItemCount() {
        return count;
    }

    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(MyItemClickListener listener) {
        myItemClickListener = listener;
    }


    //定义Viewholder
    class LinearItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvLeft, mTvRight;


        public LinearItemViewHolder(View root) {
            super(root);

            // 绑定视图
            mTvLeft = root.findViewById(R.id.tv_left);
            mTvRight = root.findViewById(R.id.tv_right);

        }

    }

    //定义Viewholder
    class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView Text;
        public ImageView image;

        public MainViewHolder(View root) {
            super(root);

            // 绑定视图
            Text = (TextView) root.findViewById(R.id.Item);
            image = (ImageView) root.findViewById(R.id.Image);

            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null) {
                                                myItemClickListener.onItemClick(v, getPosition());
                                            }
                                        }

                                    }
                    //监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getText() {
            return Text;
        }


    }
}