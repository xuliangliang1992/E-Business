package com.jinlong.ebusiness.func.loading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.dialog.DialogManager;
import com.jinlong.ebusiness.func.main.MainActivity;
import com.xll.mvplib.view.ItemClickListener;

/**
 * @author xll
 * @date 2018/9/15
 */

public class LoadingFragment extends BaseFragment {

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constant.IS_DEBUG) {
            SparseArray<String> address = new SparseArray<>();
            address.put(0, "测试环境");
            address.put(1, "生产环境");
            DialogManager.getInstance().showAddressDialog(getContext(), "地址选择", address, new ItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    switch (position) {
                        case 0:
                            RouteTo(MainActivity.class);
                            break;
                        case 1:

                            break;
                        default:

                            break;
                    }
                    //检测版本更新

                }
            });
        } else {
            //检测版本更新

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_fragment, container, false);

        return view;
    }
}
