package com.jinlong.ebusiness.func.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.base.MainApplication;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.dialog.DialogManager;
import com.jinlong.ebusiness.func.login.LoginActivity;
import com.jinlong.ebusiness.func.mine.bind.BindPhoneActivity;
import com.jinlong.ebusiness.func.mine.collection.CollectionListActivity;
import com.jinlong.ebusiness.func.mine.message.MessageListActivity;
import com.jinlong.ebusiness.func.mine.password.ModifyPasswordActivity;
import com.jinlong.ebusiness.func.mine.setting.SettingActivity;
import com.jinlong.ebusiness.func.mine.shipping.ShippingAddressListActivity;
import com.jinlong.ebusiness.func.order.OrderListActivity;
import com.xll.mvplib.utils.SharePreferenceUtil;
import com.xll.mvplib.utils.StringUtil;
import com.xll.mvplib.view.ItemClickListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/15
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.rl_not_login)
    RelativeLayout mRlNotLogin;
    @BindView(R.id.iv_head_portrait)
    ImageView mIvHeadPortrait;
    @BindView(R.id.rl_login)
    RelativeLayout mRlLogin;
    @BindView(R.id.rl_collectibles)
    LinearLayout mRlCollectibles;
    @BindView(R.id.rl_collection_store)
    LinearLayout mRlCollectionShop;
    @BindView(R.id.rl_orders)
    LinearLayout mRlOrders;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.tv_modify_password)
    TextView mTvModifyPassword;
    @BindView(R.id.tv_shipping_address)
    TextView mTvShippingAddress;
    @BindView(R.id.tv_about_company)
    TextView mTvAboutCompany;
    @BindView(R.id.tv_FAQ)
    TextView mTvFAQ;
    @BindView(R.id.tv_feedback)
    TextView mTvFeedback;
    @BindView(R.id.tv_language)
    TextView mTvLanguage;
    @BindView(R.id.tv_new_guidelines)
    TextView mTvNewGuidelines;
    Unbinder unbinder;
    @BindView(R.id.cg_login)
    Group mCgLogin;

    private String token;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = (String) SharePreferenceUtil.getInstance().get(MainApplication.getInstance().getApplicationContext(), Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.TOKEN, "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (!StringUtil.isStringNull(token)) {
            mCgLogin.setVisibility(View.VISIBLE);
            mRlNotLogin.setVisibility(View.GONE);
        } else {
            mRlNotLogin.setVisibility(View.VISIBLE);
            mCgLogin.setVisibility(View.GONE);
        }

        String language = (String) SharePreferenceUtil.getInstance().get(getActivity(), Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.LANGUAGE, Constant.CHINESE);
        if (Constant.ENGLISH.equals(language)) {
            mTvLanguage.setText(R.string.language_english);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_not_login, R.id.rl_login, R.id.rl_collectibles, R.id.rl_collection_store, R.id.rl_orders, R.id.tv_message, R.id.tv_modify_password, R.id.tv_shipping_address, R.id.tv_about_company, R.id.tv_FAQ, R.id.tv_feedback, R.id.tv_language, R.id.tv_new_guidelines})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_not_login:
                //未登录
                routeTo(LoginActivity.class);
                break;
            case R.id.rl_login:
                //已登录
                routeTo(SettingActivity.class);
                break;
            case R.id.rl_collectibles:
                //收藏的商品
                routeTo(CollectionListActivity.class);
                break;
            case R.id.rl_collection_store:
                //收藏的店铺
                routeTo(CollectionListActivity.class);
                break;
            case R.id.rl_orders:
                //我的订单
                routeTo(OrderListActivity.class);
                break;
            case R.id.tv_message:
                //消息列表
                routeTo(MessageListActivity.class);
                break;
            case R.id.tv_modify_password:
                //修改密码
                routeTo(ModifyPasswordActivity.class);
                break;
            case R.id.tv_shipping_address:
                //收货地址
                routeTo(ShippingAddressListActivity.class);
                break;
            case R.id.tv_about_company:
                //关于公司
                break;
            case R.id.tv_FAQ:
                //常见问题
                routeTo(BindPhoneActivity.class);
                break;
            case R.id.tv_feedback:
                //意见反馈
                break;
            case R.id.tv_language:
                //语言切换
                DialogManager.getInstance().showBottomListDialog(getActivity(), new String[]{getString(R.string.language_chinese), getString(R.string.language_english)}, new ItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        if (position == 0) {
                            SharePreferenceUtil.getInstance().put(getActivity(), Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.LANGUAGE, Constant.CHINESE);
                            EventBus.getDefault().post(Constant.EVENT_REFRESH_LANGUAGE);
                        } else if (position == 1) {
                            SharePreferenceUtil.getInstance().put(getActivity(), Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.LANGUAGE, Constant.ENGLISH);
                            EventBus.getDefault().post(Constant.EVENT_REFRESH_LANGUAGE);
                        }
                    }
                });
                break;
            case R.id.tv_new_guidelines:
                //新手引导
                break;
            default:
                break;
        }
    }
}
