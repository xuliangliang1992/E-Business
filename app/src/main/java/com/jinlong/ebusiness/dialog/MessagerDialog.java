package com.jinlong.ebusiness.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.xll.mvplib.dialog.DialogClickListener;
import com.xll.mvplib.dialog.base.BaseDialog;
import com.xll.mvplib.utils.StringUtil;

/**
 * @author xll
 * @date 2018/9/18
 */

public class MessagerDialog extends BaseDialog {

    private ImageView mImageView;
    private TextView mTvMessage;
    private TextView mTvCancel;
    private TextView mTvSure;

    private String message;
    private String left;
    private String right;
    private int pic = 0;
    /**
     * 控制右边按钮是否关闭当前弹框
     */
    private boolean rightDismiss = true;

    private DialogClickListener clickListener;

    public MessagerDialog(Context context, String message, DialogClickListener clickListener) {
        super(context);
        this.message = message;
        this.clickListener = clickListener;
        setCancel(false);
    }

    public MessagerDialog(Context context, String message, String left, String right, int pic, DialogClickListener clickListener) {
        super(context);
        this.message = message;
        this.left = left;
        this.right = right;
        this.pic = pic;
        this.clickListener = clickListener;
        setCancel(false);
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(context, R.layout.messager_dialog, null);
        widthScale(0.77f);
        mImageView = view.findViewById(R.id.imageView);
        mTvMessage = view.findViewById(R.id.tv_message);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvSure = view.findViewById(R.id.tv_sure);

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != clickListener) {
                    clickListener.leftClickListener();
                }
                dismiss();
            }
        });

        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != clickListener) {
                    clickListener.rightClickListener();
                }
                if (rightDismiss) {
                    dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public boolean setUiBeforeShow() {
        if (pic != 0) {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setBackgroundResource(pic);
        }
        if (!StringUtil.isStringNull(left)) {
            mTvCancel.setText(left);
        }
        if (!StringUtil.isStringNull(right)) {
            mTvCancel.setText(right);
        }
        if (!StringUtil.isStringNull(message)) {
            mTvMessage.setText(message);
        }
        return true;
    }
}
