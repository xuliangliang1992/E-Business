package com.jinlong.ebusiness.func.mine.shipping;

import android.content.Intent;
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
import android.widget.Toast;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.func.mine.shipping.edit.EditAddressActivity;
import com.jinlong.ebusiness.utils.TextUtil;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.StringUtil;
import com.xll.mvplib.utils.ToastUtil;
import com.xll.mvplib.view.ItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/18
 */

public class ShippingAddressListFragment extends BaseFragment implements ShippingAddressListContract.View {
    @BindView(R.id.rv_shipping_address)
    SwipeMenuRecyclerView mRvShippingAddress;
    Unbinder unbinder;

    private ShippingAddressListContract.Presenter mPresenter;
    private ShippingAddressAdapter shippingAddressAdapter;

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            int width = getResources().getDimensionPixelSize(R.dimen.x100);
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackground(R.color.black_f3)
                    .setImage(R.drawable.delete)
                    .setWidth(width)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                showProgressDialog();
                mPresenter.deleteConsigneeAddressByIds(HandleMapUtil.getInt(mapList.get(menuPosition), "id") + "");
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getActivity(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private List<Map<String, Object>> mapList;

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
        mRvShippingAddress.setSwipeMenuCreator(swipeMenuCreator);
        mRvShippingAddress.setSwipeMenuItemClickListener(mMenuItemClickListener);
        shippingAddressAdapter = new ShippingAddressAdapter();
        mRvShippingAddress.setAdapter(shippingAddressAdapter);
        shippingAddressAdapter.notifyDataSetChanged();

        shippingAddressAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Map<String, Object> map = mapList.get(position);
                Intent intent = new Intent(getActivity(), EditAddressActivity.class);
                intent.putExtra(Constant.SOURCE, Constant.EDIT_ADDRESS_ACTIVITY);
                intent.putExtra(Constant.MAP_DATA, (Serializable) map);
                startActivityForResult(intent, Constant.REQUEST);
            }
        });

        requestData();
        return view;
    }

    private void requestData() {
        showProgressDialog();
        mPresenter.getConsigneeAddressList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.unSubscriber();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST && resultCode == Constant.RESULT_CONSIGNEE_ADDRESS) {
            requestData();
        }
    }

    @OnClick(R.id.btn_add_shipping_address)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), EditAddressActivity.class);
        intent.putExtra(Constant.SOURCE, Constant.ADD_ADDRESS_ACTIVITY);
        startActivityForResult(intent, Constant.REQUEST);
    }

    @Override
    public void getConsigneeAddressListRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                mapList = (List<Map<String, Object>>) map.get("data");
                shippingAddressAdapter.setData(mapList);
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void deleteConsigneeAddressByIdsRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                requestData();
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void setPresenter(ShippingAddressListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressViewHolder> {

        private List<Map<String, Object>> data;
        private ItemClickListener mItemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            mItemClickListener = itemClickListener;
        }

        public void setData(List<Map<String, Object>> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ShippingAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.shipping_address_item, parent, false);
            return new ShippingAddressViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ShippingAddressViewHolder holder, final int position) {
            String name = HandleMapUtil.getString(data.get(position), "name");
            holder.tvName.setText(StringUtil.emptyIs(name));
            String phone = HandleMapUtil.getString(data.get(position), "phone");
            holder.tvPhone.setText(StringUtil.emptyIs(phone));
            String address = HandleMapUtil.getString(data.get(position), "address");
            int isDefault = HandleMapUtil.getInt(data.get(position), "isDefault");
            if (isDefault == 1) {
                TextUtil.setPartOfFontColor(" " + getString(R.string.text_default) + " " + address, 0, 4, ContextCompat.getColor(getActivity(), R.color.default_bg),
                        0, 4, ContextCompat.getColor(getActivity(), R.color.default_text_color), holder.tvAddress);
            } else {
                holder.tvAddress.setText(StringUtil.emptyIs(address));
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClickListener(v, position);
                }
            });

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