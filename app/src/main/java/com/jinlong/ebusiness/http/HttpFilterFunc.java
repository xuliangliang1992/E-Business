package com.jinlong.ebusiness.http;

import android.os.Handler;
import android.os.Looper;

import com.jinlong.ebusiness.base.MainApplication;
import com.jinlong.ebusiness.dialog.DialogManager;
import com.xll.mvplib.subscriber.FilterHandlerListener;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.ToastUtil;

import java.util.Map;

import io.reactivex.functions.Predicate;


/**
 * @author xll
 * @date 2018/9/20
 */
public class HttpFilterFunc implements Predicate<Map<String, Object>> {

    private FilterHandlerListener filterHandlerListener;

    public HttpFilterFunc(){

    }

    public HttpFilterFunc(FilterHandlerListener filterHandlerListener){
        this.filterHandlerListener = filterHandlerListener;
    }

    @Override
    public boolean test(Map<String, Object> map) throws Exception {
        if (map == null) {
            return false;
        }
        final int code = HandleMapUtil.getInt(map,"code");
        if (code!=0) {
            final String msg = HandleMapUtil.getString(map, "msg");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    DialogManager.getInstance().dismissProgressHUD();
                    if (null != filterHandlerListener){
                        filterHandlerListener.handleFilter(code,msg);
                    }else {
                        ToastUtil.showToast(MainApplication.getInstance().getApplicationContext(),msg);
                    }
                }
            });
            return false;
        }
        return true;
    }
}
