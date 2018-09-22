package com.jinlong.ebusiness.func.mine.shipping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.func.mine.shipping.edit.EditAddressListActivity;
import com.jinlong.ebusiness.utils.TextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/18
 */

public class ShippingAddressListFragment extends BaseFragment {
    @BindView(R.id.rv_shipping_address)
    RecyclerView mRvShippingAddress;
    Unbinder unbinder;

    private ShippingAddressAdapter shippingAddressAdapter;

    public static ShippingAddressListFragment newInstance() {
        return new ShippingAddressListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_address_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRvShippingAddress.setLayoutManager(new LinearLayoutManager(getActivity()));
        shippingAddressAdapter = new ShippingAddressAdapter();
        mRvShippingAddress.setAdapter(shippingAddressAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_add_shipping_address)
    public void onViewClicked() {
        routeTo(EditAddressListActivity.class);
    }

    class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressViewHolder> {

        @NonNull
        @Override
        public ShippingAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.shipping_address_item, parent, false);
            return new ShippingAddressViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public void onBindViewHolder(@NonNull ShippingAddressViewHolder holder, int position) {
            String content = " 默认 7号楼5楼上海市浦东新碧波路619号微电子港新碧波路619号微电子港新碧波路619号微电子港新碧波路619号微电子港7号楼5楼";

            TextUtil.setPartOfFontColor(content,0,4, ContextCompat.getColor(getActivity(),R.color.default_bg),
            0,4,ContextCompat.getColor(getActivity(),R.color.default_text_color),holder.tvAddress);



        }
    }

    static class ShippingAddressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        public ShippingAddressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
