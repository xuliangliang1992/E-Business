package com.jinlong.ebusiness.func.login.register;

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
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.utils.TextChangeUtil;
import com.xll.mvplib.utils.CheckRegUtil;
import com.xll.mvplib.utils.HandleMapUtil;
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
 * @date 2018/9/17
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_password_again)
    EditText mEtPasswordAgain;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    Unbinder unbinder;
    private RegisterContract.Presenter mPresenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        List<EditText> list = new ArrayList<>();
        list.add(mEtEmail);
        list.add(mEtPassword);
        list.add(mEtPasswordAgain);
        List<Integer> lengthList = new ArrayList<>();
        lengthList.add(1);
        lengthList.add(6);
        lengthList.add(6);
        mEtEmail.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnRegister));
        mEtPassword.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnRegister));
        mEtPasswordAgain.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnRegister));
        return view;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void registerSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                toLoginActivity();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String email = mEtEmail.getText().toString().trim();
        String pwd = mEtPassword.getText().toString().trim();
        String pwd2 = mEtPasswordAgain.getText().toString().trim();

        if (!CheckRegUtil.isEmail(email)) {
            ToastUtil.showToast(getActivity(), getString(R.string.error_email));
            return;
        }
        if (!pwd.equals(pwd2)) {
            ToastUtil.showToast(getActivity(), getString(R.string.pwd_differ));
            return;
        }
        showProgressDialog();
        mPresenter.register(email, pwd);
    }
}
