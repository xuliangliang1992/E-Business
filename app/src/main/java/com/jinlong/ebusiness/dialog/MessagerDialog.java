package com.jinlong.ebusiness.dialog;

import android.content.Context;
import android.view.View;

import com.xll.mvplib.dialog.base.BaseDialog;

/**
 * @author xll
 * @date 2018/9/18
 */

public class MessagerDialog extends BaseDialog {

    public MessagerDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        return null;
    }

    @Override
    public boolean setUiBeforShow() {
        return false;
    }
}
