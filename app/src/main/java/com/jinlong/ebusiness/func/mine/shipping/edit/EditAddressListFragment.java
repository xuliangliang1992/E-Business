package com.jinlong.ebusiness.func.mine.shipping.edit;

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
 * @date 2018/9/18
 */

public class EditAddressListFragment extends BaseFragment {
    public static EditAddressListFragment newInstance() {
        return new EditAddressListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_edit_address_fragment, container, false);

        return view;
    }
}
