package com.jinlong.ebusiness.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.xll.mvplib.dialog.DialogClickListener;
import com.xll.mvplib.dialog.base.BaseDialog;
import com.xll.mvplib.utils.StringUtil;

/**
 * @author xll
 * @date 2018/9/17
 */

public class TipsDialog extends BaseDialog {
    private TextView mTvMessage;
    private TextView mTvTitle;
    private TextView mTvSure;

    private String title;
    private String message;
    private String btnText;
    private DialogClickListener clickListener;

    public TipsDialog(Context context, String title, String message, String btnText, DialogClickListener clickListener) {
        super(context);
        this.title = title;
        this.message = message;
        this.btnText = btnText;
        this.clickListener = clickListener;
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(context, R.layout.tips_dialog, null);
        widthScale(0.77f);
        mTvMessage = view.findViewById(R.id.tv_message);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvSure = view.findViewById(R.id.tv_sure);
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != clickListener) {
                    clickListener.rightClickListener();
                }
                  dismiss();

            }
        });
        return view;
    }

    @Override
    public boolean setUiBeforeShow() {
        if(!StringUtil.isStringNull(title)){
            mTvTitle.setText(title);
        }
        if(!StringUtil.isStringNull(btnText)){
            mTvSure.setText(btnText);
        }if(!StringUtil.isStringNull(message)){
            mTvMessage.setText(message);
        }
        return true;
    }
}
