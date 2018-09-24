package com.jinlong.ebusiness.func.mine.shipping.edit;

import com.jinlong.ebusiness.http.request.LoanDataSource;
import com.jinlong.ebusiness.http.subscriber.SaicObserver;
import com.xll.mvplib.schedulers.BaseSchedulerProvider;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @aucthor xll
 * @date 2018/9/23 0023
 */
public class EditAddressPresenter implements EditAddressContract.Presenter {

    private EditAddressContract.View mView;
    private CompositeDisposable mCompositeSubscription;
    private BaseSchedulerProvider mSchedulerProvider;
    private LoanDataSource mLoanDataSource;

    EditAddressPresenter(LoanDataSource loanDataSource, EditAddressContract.View view, BaseSchedulerProvider schedulerProvider) {
        this.mView = view;
        this.mLoanDataSource = loanDataSource;
        this.mSchedulerProvider = schedulerProvider;
        mView.setPresenter(this);
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void subscriber() {

    }

    @Override
    public void unSubscriber() {
        mCompositeSubscription.clear();
    }

    @Override
    public void addConsigneeAddress(String name, String phone, String provinceId, String address, int isDefault) {
        mCompositeSubscription.clear();
        mLoanDataSource.addConsigneeAddress(name, phone, provinceId, address, isDefault)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.addConsigneeAddressRequestSuccess(map);
                    }
                });
    }

    @Override
    public void editConsigneeAddress(int id,String name, String phone, String provinceId, String address, int isDefault) {
        mCompositeSubscription.clear();
        mLoanDataSource.editConsigneeAddress(id, name, phone, provinceId, address, isDefault)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.editConsigneeAddressRequestSuccess(map);
                    }
                });
    }

    @Override
    public void getCity() {
        mCompositeSubscription.clear();
        mLoanDataSource.getCity()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.getCityRequestSuccess(map);
                    }
                });
    }
}
