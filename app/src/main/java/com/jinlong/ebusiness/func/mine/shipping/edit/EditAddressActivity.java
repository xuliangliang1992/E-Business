package com.jinlong.ebusiness.func.mine.shipping.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseActivity;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.injection.Injection;
import com.xll.mvplib.utils.ActivityUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author xll
 * @date 2018/9/18
 */

public class EditAddressActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int source = getIntent().getIntExtra(Constant.SOURCE, 0);
        if (Constant.ADD_ADDRESS_ACTIVITY == source) {
            setTitle(getString(R.string.add_shipping_address));
        }
        if (Constant.EDIT_ADDRESS_ACTIVITY == source) {
            setTitle(getString(R.string.modify_shipping_address));
        }

        EditAddressFragment fragment = (EditAddressFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (null == fragment) {
            fragment = EditAddressFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_content);
        }

        new EditAddressPresenter(Injection.provideLoanRepository(), fragment, Injection.provideSchedulerProvider());

    }

    @Override
    @Subscribe
    public void onEvent(String str) {
        super.onEvent(str);
    }
}
