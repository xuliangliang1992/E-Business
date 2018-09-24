package com.jinlong.ebusiness.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.Window;

import com.jinlong.ebusiness.R;
import com.xll.mvplib.dialog.BottomListDialog;
import com.xll.mvplib.dialog.DialogClickListener;
import com.xll.mvplib.dialog.MiddleListDialog;
import com.xll.mvplib.dialog.base.BaseDialog;
import com.xll.mvplib.view.ItemClickListener;
import com.xll.mvplib.view.progresshud.ProgressHUD;

/**
 * @author xll
 * @date 2018/9/15
 */

public class DialogManager {
    private static DialogManager dialogManager;

    private ProgressHUD hud;

    public BaseDialog mDialog;

    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (null == dialogManager) {
            synchronized (DialogManager.class) {
                if (null == dialogManager) {
                    dialogManager = new DialogManager();
                }
            }
        }
        return dialogManager;
    }

    /**
     * 测试地址选择
     *
     * @param context
     * @param title
     * @param listData
     * @param itemClickListener
     */
    public void showAddressDialog(Context context, String title, SparseArray<String> listData, ItemClickListener itemClickListener) {
        mDialog = new MiddleListDialog(context, title, listData, itemClickListener);
        mDialog.show();
    }

    public void showTipsDialog(Context context, String title, String message, String btnText, DialogClickListener clickListener) {
        mDialog = new TipsDialog(context, title, message, btnText, clickListener);
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setWindowAnimations(R.style.dialogWindowAnim);
    }

    public void showMessagerDialog(Context context, String message, DialogClickListener clickListener) {
        mDialog = new MessagerDialog(context, message, clickListener);
        mDialog.show();
    }

    public void showMessagerDialog(Context context, String message, String left, String right, int pic, DialogClickListener clickListener) {
        mDialog = new MessagerDialog(context, message, left, right, pic, clickListener);
        mDialog.show();
    }

    public void showBottomListDialog(Context context, String[] titles, ItemClickListener itemClickListener) {
        mDialog = new BottomListDialog(context, titles, itemClickListener);
        mDialog.show();
    }

    public void showProgressHUD(Context context) {

        hud = ProgressHUD.create(context)
                .setStyle(ProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在通讯中,请稍候...");
        if (!hud.isShowing()) {
            hud.show();
        }

    }

    public void dismissProgressHUD() {
        if (null != hud) {
            hud.dismiss();
            hud = null;
        }
    }
}
