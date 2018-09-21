package com.jinlong.ebusiness.func.mine.password;

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
import com.jinlong.ebusiness.utils.TextChangeUtil;
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
 * @date 2018/9/18
 */

public class ModifyPasswordFragment extends BaseFragment implements ModifyPasswordContract.View {

    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_new_password_again)
    EditText mEtNewPasswordAgain;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    Unbinder unbinder;

    private ModifyPasswordContract.Presenter mPresenter;

    public static ModifyPasswordFragment newInstance() {
        return new ModifyPasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modify_password_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        List<EditText> list = new ArrayList<>();
        list.add(mEtOldPassword);
        list.add(mEtNewPassword);
        list.add(mEtNewPasswordAgain);
        mEtOldPassword.addTextChangedListener(new TextChangeUtil(list, mBtnSure));
        mEtNewPassword.addTextChangedListener(new TextChangeUtil(list, mBtnSure));
        mEtNewPasswordAgain.addTextChangedListener(new TextChangeUtil(list, mBtnSure));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.unSubscriber();
    }

    @OnClick(R.id.btn_sure)
    public void onViewClicked() {
        String oldPwd = mEtOldPassword.getText().toString().trim();
        String newPwd = mEtNewPassword.getText().toString().trim();
        String newPwd2 = mEtNewPasswordAgain.getText().toString().trim();

        if (!newPwd.equals(newPwd2)) {
            ToastUtil.showToast(getActivity(), getString(R.string.pwd_differ));
            return;
        }

        showProgressDialog();
        mPresenter.modifyPwd(oldPwd, newPwd);
    }

    @Override
    public void setPresenter(ModifyPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void modifyPwdRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        if (code == 0) {
            toLoginActivity();
        }
    }
}
