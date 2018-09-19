package com.jinlong.ebusiness.func.login.success;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;

/**
 * @author xll
 * @date 2018/9/19
 */

public class LoginSuccessFragment extends BaseFragment {

    public static LoginSuccessFragment newInstance() {
        return new LoginSuccessFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_success_fragment, container, false);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
