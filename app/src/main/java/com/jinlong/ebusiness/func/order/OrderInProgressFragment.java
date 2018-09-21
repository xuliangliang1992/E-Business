package com.jinlong.ebusiness.func.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;

/**
 * @author xll
 * @date 2018/9/21
 */

public class OrderInProgressFragment extends BaseFragment {

    public static OrderInProgressFragment newInstance() {
        return new OrderInProgressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);

        return view;
    }
}
