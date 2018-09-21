package com.jinlong.ebusiness.func.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.base.MainApplication;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.func.login.password.ForgetPasswordActivity;
import com.jinlong.ebusiness.func.login.register.RegisterActivity;
import com.jinlong.ebusiness.utils.TextChangeUtil;
import com.xll.mvplib.utils.CheckRegUtil;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.SharePreferenceUtil;
import com.xll.mvplib.utils.StringUtil;
import com.xll.mvplib.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/19
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.cb_agree)
    CheckBox mCbAgree;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    Unbinder unbinder;
    String email, pwd;
    private LoginContract.Presenter mPresenter;

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
        List<EditText> list = new ArrayList<>();
        list.add(mEtEmail);
        list.add(mEtPassword);
        mEtEmail.addTextChangedListener(new TextChangeUtil(list, mBtnLogin, mCbAgree));
        mEtPassword.addTextChangedListener(new TextChangeUtil(list, mBtnLogin, mCbAgree));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.unSubscriber();
    }

    @OnClick({R.id.cb_agree, R.id.tv_forget_password, R.id.btn_login, R.id.iv_face_book, R.id.iv_g_mail, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_agree:
                email = mEtEmail.getText().toString().trim();
                pwd = mEtPassword.getText().toString().trim();
                if (!StringUtil.isStringNull(email) && email.length() > 5
                        && !StringUtil.isStringNull(pwd) && pwd.length() > 5
                        && mCbAgree.isChecked()) {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_btn_click);
                    mBtnLogin.setEnabled(true);
                } else {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_btn_un_click);
                    mBtnLogin.setEnabled(false);
                }
                break;
            case R.id.tv_forget_password:
                routeTo(ForgetPasswordActivity.class);
                break;
            case R.id.btn_login:
                email = mEtEmail.getText().toString().trim();
                pwd = mEtPassword.getText().toString().trim();
                if (!CheckRegUtil.isEmail(email)) {
                    ToastUtil.showToast(getActivity(), getString(R.string.error_email));
                    return;
                }
                showProgressDialog();
                mPresenter.login(email, pwd);
                break;
            case R.id.iv_face_book:
                break;
            case R.id.iv_g_mail:
                break;
            case R.id.tv_register:
                routeTo(RegisterActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loginSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        String token = HandleMapUtil.getString(data, "token");
        SharePreferenceUtil.getInstance().put(MainApplication.getInstance().getApplicationContext(), Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.TOKEN, token);

        toMainActivity();
    }
}
