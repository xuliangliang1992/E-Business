package com.jinlong.ebusiness.func.mine.bind;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.ToastUtil;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/22
 */

public class BindPhoneFragment extends BaseFragment implements BindPhoneContract.View{

    private BindPhoneContract.Presenter mPresenter;

    public static BindPhoneFragment newInstance() {
        return new BindPhoneFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bind_phone_fragment, container, false);

        return view;
    }

    @Override
    public void setPresenter(BindPhoneContract.Presenter presenter) {
        this.mPresenter=presenter;
    }

    @Override
    public void bindPhoneRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                ToastUtil.showToast(getActivity(),"绑定成功");
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }
}
