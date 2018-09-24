package com.jinlong.ebusiness.func.login.password;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.dialog.DialogManager;
import com.jinlong.ebusiness.utils.TextChangeUtil;
import com.xll.mvplib.dialog.DialogClickListener;
import com.xll.mvplib.utils.CheckRegUtil;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.ToastUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ForgetPasswordFragment extends BaseFragment implements ForgetPasswordContract.View {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.btn_forget_pwd)
    Button mBtnForgetPwd;
    Unbinder unbinder;
    private ForgetPasswordContract.Presenter mPresenter;

    public static ForgetPasswordFragment newInstance() {
        return new ForgetPasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_password_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        mEtEmail.addTextChangedListener(new TextChangeUtil(mEtEmail, 1, mBtnForgetPwd));
        return view;
    }

    @Override
    public void setPresenter(ForgetPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void forgetPwdRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                DialogManager.getInstance().showTipsDialog(getActivity(), "找回密码成功",
                        msg, "去登录", new DialogClickListener() {
                            @Override
                            public void leftClickListener() {

                            }

                            @Override
                            public void rightClickListener() {
                                toLoginActivity();
                            }
                        }
                );
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_forget_pwd)
    public void onViewClicked() {
        String email = mEtEmail.getText().toString().trim();

        if (!CheckRegUtil.isEmail(email)) {
            ToastUtil.showToast(getActivity(), getString(R.string.error_email));
            return;
        }

        showProgressDialog();
        mPresenter.forgetPwd(email);
    }
}
