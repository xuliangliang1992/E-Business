package com.jinlong.ebusiness.func.mine.shipping.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinlong.ebusiness.R;
import com.jinlong.ebusiness.base.BaseFragment;
import com.jinlong.ebusiness.constant.Constant;
import com.jinlong.ebusiness.utils.TextChangeUtil;
import com.xll.mvplib.utils.HandleMapUtil;
import com.xll.mvplib.utils.StringUtil;
import com.xll.mvplib.utils.ToastUtil;
import com.xll.mvplib.view.SwitchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xll
 * @date 2018/9/18
 */

public class EditAddressFragment extends BaseFragment implements EditAddressContract.View {

    @BindView(R.id.tv_phone_area)
    TextView mTvPhoneArea;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_region)
    TextView mTvRegion;
    @BindView(R.id.et_address_detail)
    EditText mEtAddressDetail;
    @BindView(R.id.sv_default_address)
    SwitchView mSvDefaultAddress;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    @BindView(R.id.et_consignee_name)
    EditText mEtConsigneeName;
    @BindView(R.id.rl_default_address)
    RelativeLayout mRlDefaultAddress;
    Unbinder unbinder;
    private EditAddressContract.Presenter mPresenter;
    private int source, id, isDefault;
    private Map<String, Object> map;
    private String name, phone, address;

    public static EditAddressFragment newInstance() {
        return new EditAddressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        source = getActivity().getIntent().getIntExtra(Constant.SOURCE, 0);
        map = (Map<String, Object>) getActivity().getIntent().getSerializableExtra(Constant.MAP_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_edit_address_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initValue();
        initListener();
        return view;
    }

    private void initValue() {
        if (Constant.EDIT_ADDRESS_ACTIVITY == source && map != null) {
            id = HandleMapUtil.getInt(map, "id");
            name = HandleMapUtil.getString(map, "name");
            mEtConsigneeName.setText(StringUtil.emptyIs(name));
            phone = HandleMapUtil.getString(map, "phone");
            mEtPhone.setText(StringUtil.emptyIs(phone));
            address = HandleMapUtil.getString(map, "address");
            mEtAddressDetail.setText(StringUtil.emptyIs(address));
            isDefault = HandleMapUtil.getInt(map, "isDefault");
            mSvDefaultAddress.setOpened(isDefault != 1 ? false : true);
        }
    }

    private void initListener() {
        List<EditText> list = new ArrayList<>();
        list.add(mEtConsigneeName);
        list.add(mEtPhone);
        list.add(mEtAddressDetail);
        List<Integer> lengthList = new ArrayList<>();
        lengthList.add(1);
        lengthList.add(10);
        lengthList.add(1);
        mEtConsigneeName.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnCommit));
        mEtPhone.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnCommit));
        mEtAddressDetail.addTextChangedListener(new TextChangeUtil(list, lengthList, mBtnCommit));
    }

    @Override
    public void editConsigneeAddressRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                getActivity().setResult(Constant.RESULT_CONSIGNEE_ADDRESS);
                getActivity().finish();
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void addConsigneeAddressRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                getActivity().setResult(Constant.RESULT_CONSIGNEE_ADDRESS);
                getActivity().finish();
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void getCityRequestSuccess(Map<String, Object> map) {
        dismissProgressDialog();
        int code = HandleMapUtil.getInt(map, "code");
        String msg = HandleMapUtil.getString(map, "msg");
        switch (code) {
            case 0:
                List<Map<String, Object>> cityList = (List<Map<String, Object>>) map.get("data");
                //省市区弹框未做
                break;
            case Constant.UNAUTHORIZED:
                toLoginActivity();
                break;
            default:
                ToastUtil.showToast(getActivity(), msg);
                break;
        }
    }

    @Override
    public void setPresenter(EditAddressContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.unSubscriber();
    }

    @OnClick({R.id.tv_phone_area, R.id.tv_region, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone_area:
                break;
            case R.id.tv_region:
                showProgressDialog();
                mPresenter.getCity();
                break;
            case R.id.btn_commit:
                name = mEtConsigneeName.getText().toString().trim();
                phone = mEtPhone.getText().toString().trim();
                address = mEtAddressDetail.getText().toString().trim();
                isDefault = mSvDefaultAddress.isOpened() ? 1 : 0;
                showProgressDialog();
                if (Constant.ADD_ADDRESS_ACTIVITY == source) {
                    mPresenter.addConsigneeAddress(name, phone, "1007", address, isDefault);
                }
                if (Constant.EDIT_ADDRESS_ACTIVITY == source) {
                    mPresenter.editConsigneeAddress(id, name, phone, "1007", address, isDefault);
                }
                break;
        }
    }
}
