package com.jinlong.ebusiness.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jinlong.ebusiness.R;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(Context context, String title, String message) {
        cancelToast();
        if (context != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.tips_dialog, null);
            ((TextView) layout.findViewById(R.id.tv_title)).setText(title);
            ((TextView) layout.findViewById(R.id.tv_message)).setText(message);
            mToast = new Toast(context);
            mToast.setView(layout);
            mToast.setGravity(Gravity.CENTER, 0, 20);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
