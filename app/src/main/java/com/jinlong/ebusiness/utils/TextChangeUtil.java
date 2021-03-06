package com.jinlong.ebusiness.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jinlong.ebusiness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听多个EditText控制Button的可不可点
 *
 * @author xll
 * @date 2018/9/20 0020.
 */

public class TextChangeUtil implements TextWatcher {

    private TextView btn;
    private CheckBox cb;
    private List<EditText> mList = new ArrayList<>();
    private List<Integer> lengthList = new ArrayList<>();
    private int length;
    private int btnEnableBackground;
    private int btnDisabledBackground;

    public TextChangeUtil(EditText editText, int length, TextView btn) {
        mList.add(editText);
        lengthList.add(length);
        this.btn = btn;
        this.btnEnableBackground = R.drawable.shape_btn_click;
        this.btnDisabledBackground = R.drawable.shape_btn_un_click;
    }

    public TextChangeUtil(EditText editText, int length, TextView btn, int btnEnableBackground, int btnDisabledBackground) {
        this(editText, length, btn);
        this.btnEnableBackground = btnEnableBackground;
        this.btnDisabledBackground = btnDisabledBackground;
    }

    public TextChangeUtil(List<EditText> mList, List<Integer> lengthList, TextView btn) {
        this.mList = mList;
        this.lengthList = lengthList;
        this.btn = btn;
        this.btnEnableBackground = R.drawable.shape_btn_click;
        this.btnDisabledBackground = R.drawable.shape_btn_un_click;
    }

    public TextChangeUtil(List<EditText> mList, List<Integer> lengthList, TextView btn, int btnEnableBackground, int btnDisabledBackground) {
        this(mList, lengthList, btn);
        this.btnEnableBackground = btnEnableBackground;
        this.btnDisabledBackground = btnDisabledBackground;
    }

    public TextChangeUtil(List<EditText> mList, List<Integer> lengthList, TextView btn, CheckBox cb) {
        this(mList, lengthList, btn);
        this.cb = cb;
    }

    public TextChangeUtil(List<EditText> mList, List<Integer> lengthList, TextView btn, CheckBox cb, int btnEnableBackground, int btnDisabledBackground) {
        this(mList, lengthList, btn, btnEnableBackground, btnDisabledBackground);
        this.cb = cb;
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
                if (mList.get(i).getText().length() > lengthList.get(i)) {
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
