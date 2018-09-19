package com.jinlong.ebusiness.dialog;

import android.content.Context;
import android.view.View;

import com.xll.mvplib.dialog.base.BaseDialog;

/**
 * @author xll
 * @date 2018/9/17
 */

public class TipsDialog extends BaseDialog {
    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public TipsDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.77f);
        return null;
    }

    @Override
    public boolean setUiBeforeShow() {
        return false;
    }
}
