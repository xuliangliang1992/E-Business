package com.jinlong.ebusiness.func.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.func.login.mail.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/19
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.cb_agree)
    CheckBox mCbAgree;
    Unbinder unbinder;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cb_agree, R.id.tv_forget_password, R.id.btn_login, R.id.iv_face_book, R.id.iv_g_mail, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_agree:
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.btn_login:
                break;
            case R.id.iv_face_book:
                break;
            case R.id.iv_g_mail:
                break;
            case R.id.tv_register:
                RouteTo(RegisterActivity.class);
                break;
        }
    }
}
