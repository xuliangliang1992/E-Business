package com.jinlong.ebusiness.func.mine.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.adapter.ItemCheckedChangedListener;
import com.jinlong.ebusiness.adapter.MessageListAdapter;
import com.jinlong.ebusiness.base.BaseLazyFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ToastUtil;
import com.xll.mvplib.view.ItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/21
 */

public class NotificationFragment extends BaseLazyFragment implements MessageListContract.View{

    @BindView(R.id.rv_message)
    SwipeMenuRecyclerView mRvShippingAddress;
    Unbinder unbinder;
    private MessageListContract.Presenter mPresenter;
    private MessageListAdapter messageAdapter;
    private List<Map<String, Object>> mapList;
    private int page = 1;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MessageListPresenter(Injection.provideLoanRepository(), this, Injection.provideSchedulerProvider());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        mRvShippingAddress.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvShippingAddress.setSwipeMenuCreator(swipeMenuCreator);
        mRvShippingAddress.setSwipeMenuItemClickListener(mMenuItemClickListener);
        messageAdapter = new MessageListAdapter(getActivity());
        mRvShippingAddress.setAdapter(messageAdapter);

        messageAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                /*Map<String, Object> map = mapList.get(position);
                Intent intent = new Intent(getActivity(), EditAddressActivity.class);
                intent.putExtra(Constant.SOURCE, Constant.EDIT_ADDRESS_ACTIVITY);
                intent.putExtra(Constant.MAP_DATA, (Serializable) map);
                startActivityForResult(intent, Constant.REQUEST);*/
            }
        }, new ItemCheckedChangedListener() {
            @Override
            public void onItemCheckedChangedListener(View view, int position, boolean isChecked) {

            }
        });
        return view;
    }

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
//                mPresenter.deleteConsigneeAddressByIds(HandleMapUtil.getInt(mapList.get(menuPosition), "id") + "");
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getActivity(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getMessageListRequestSuccess(BaseResponse<MessageListBean> messageListBean) {
        dismissProgressDialog();
        int code = messageListBean.getCode();
        String msg = messageListBean.getMsg();
        switch (code) {
            case 0:
                messageAdapter.setData(messageListBean.getData().getContent());
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
    public void deleteMessageByIdsRequestSuccess(Map<String, Object> map) {

    }

    @Override
    public void readMessageByIdsRequestSuccess(Map<String, Object> map) {

    }

    @Override
    public void setPresenter(MessageListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onLazyLoad() {
        showProgressDialog();
        mPresenter.getMessageList(2, page, Constant.PAGE_SIZE);
    }
}
