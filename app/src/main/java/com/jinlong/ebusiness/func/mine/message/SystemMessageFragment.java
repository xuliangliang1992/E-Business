package com.jinlong.ebusiness.func.mine.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.adapter.ItemCheckedChangedListener;
import com.jinlong.ebusiness.adapter.MessageListAdapter;
import com.jinlong.ebusiness.base.BaseLazyFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;
import com.jinlong.ebusiness.injection.Injection;
import com.orhanobut.logger.Logger;
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

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/21
 */

public class SystemMessageFragment extends BaseLazyFragment implements MessageListContract.View {

    @BindView(R.id.rv_message)
    SwipeMenuRecyclerView mRvMessage;
    @BindView(R.id.cg_all_choose)
    Group mCgAllChoose;
    Unbinder unbinder;
    @BindView(R.id.cb_all_choose)
    CheckBox mCbAllChoose;
    private MessageListContract.Presenter mPresenter;
    private MessageListAdapter messageAdapter;
    private List<MessageListBean.ContentBean> data;
    private int page = 1;
    private SparseBooleanArray chooseArray;
    private boolean isAllChoose = true;
    public static SystemMessageFragment newInstance() {
        return new SystemMessageFragment();
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

        mRvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMessage.setSwipeMenuCreator(swipeMenuCreator);
        mRvMessage.setSwipeMenuItemClickListener(mMenuItemClickListener);
        messageAdapter = new MessageListAdapter(getActivity());
        mRvMessage.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
        messageAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                //去消息详情  并且调用已读接口

            }
        }, new ItemCheckedChangedListener() {
            @Override
            public void onItemCheckedChangedListener(View view, int position, boolean isChecked) {
                setReadStatus(position, isChecked);
            }
        });

        return view;
    }

    private void setReadStatus(int position, boolean isChecked) {
        chooseArray.put(position, isChecked);

        for (int i = 0; i < chooseArray.size(); i++) {
            if(!chooseArray.get(i)){
                isAllChoose=false;
                break;
            }
        }
        mCbAllChoose.setChecked(isAllChoose);
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
                mPresenter.deleteMessageByIds(data.get(menuPosition).getMessage().getId() + "");
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
                this.data = messageListBean.getData().getContent();
                messageAdapter.setData(data);
                messageAdapter.notifyDataSetChanged();
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
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                onLazyLoad();
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
    public void readMessageByIdsRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                onLazyLoad();
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
    public void setPresenter(MessageListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onLazyLoad() {
        showProgressDialog();
        mPresenter.getMessageList(1, page, Constant.PAGE_SIZE);
    }

    /**
     * @param isDone 右上角是否是 完成
     */
    public void manageMessage(boolean isDone) {

        if (isDone) {
            mCgAllChoose.setVisibility(View.GONE);
            messageAdapter.setEdit(false);
            messageAdapter.notifyDataSetChanged();
        } else {
            chooseArray = new SparseBooleanArray();
            for (int i = 0; i < data.size(); i++) {
                chooseArray.put(i, false);
            }
            Logger.e(data.size()+"----"+chooseArray.size());
            mCgAllChoose.setVisibility(View.VISIBLE);
            messageAdapter.setEdit(true);
            messageAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.cb_all_choose, R.id.iv_read, R.id.iv_delete})
    public void onViewClicked(View view) {

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < chooseArray.size(); i++) {
            if (chooseArray.get(i)) {
                ids.append(data.get(i).getId() + ",");
            }
        }

        switch (view.getId()) {
            case R.id.cb_all_choose:
                isAllChoose=!isAllChoose;
                messageAdapter.setAllChoose(isAllChoose);
                break;
            case R.id.iv_delete:
                //删除
                if (!StringUtil.isStringNull(ids.toString())) {
                    showProgressDialog();
                    mPresenter.deleteMessageByIds(ids.toString());
                }
                break;
            case R.id.iv_read:
                //置为已读
                if (!StringUtil.isStringNull(ids.toString())) {
                    showProgressDialog();
                    mPresenter.readMessageByIds(ids.toString());
                }
                break;
        }
    }
}
