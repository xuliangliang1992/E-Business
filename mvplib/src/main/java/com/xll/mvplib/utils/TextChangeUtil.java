package com.xll.mvplib.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听多个EditText控制Button的可不可点
 *
 * @aucthor xll.
 * @date 2018/9/20 0020.
 */

public class TextChangeUtil implements TextWatcher {

    private TextView btn;
    private CheckBox cb;
    private List<EditText> mList = new ArrayList<>();
    private int btnEnableBackground;
    private int btnDisabledBackground;

    public TextChangeUtil(EditText editText, TextView btn, int btnEnableBackground, int btnDisabledBackground) {
        mList.add(editText);
        this.btn = btn;
        this.btnEnableBackground = btnEnableBackground;
        this.btnDisabledBackground = btnDisabledBackground;
    }

    public TextChangeUtil(List<EditText> mList, TextView btn, int btnEnableBackground, int btnDisabledBackground) {
        this.mList = mList;
        this.btn = btn;
        this.btnEnableBackground = btnEnableBackground;
        this.btnDisabledBackground = btnDisabledBackground;
    }

    public TextChangeUtil(List<EditText> mList, TextView btn, CheckBox cb, int btnEnableBackground, int btnDisabledBackground) {
        this.mList = mList;
        this.btn = btn;
        this.cb = cb;
        this.btnEnableBackground = btnEnableBackground;
        this.btnDisabledBackground = btnDisabledBackground;
    }

    @Override
    public void afterTextChanged(Editable arg0) {

    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

    }

    @Override
    public void onTextChanged(CharSequence cs, int start, int before,
                              int count) {
        for (int i = 0; i < mList.size(); i++) {
            if (cb != null && !cb.isChecked()) {
                btn.setBackgroundResource(btnDisabledBackground);
                btn.setEnabled(false);
                break;
            } else {
                if (mList.get(i).getText().length() > 6) {
                    if (i + 1 == mList.size()) {
                        btn.setBackgroundResource(btnEnableBackground);
                        btn.setEnabled(true);
                    }
                } else {
                    btn.setBackgroundResource(btnDisabledBackground);
                    btn.setEnabled(false);
                    break;
                }
            }
        }
    }
}
