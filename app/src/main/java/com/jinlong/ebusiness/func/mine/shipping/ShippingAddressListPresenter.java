package com.jinlong.ebusiness.func.mine.shipping;

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
public class ShippingAddressListPresenter implements ShippingAddressListContract.Presenter {

    private ShippingAddressListContract.View mView;
    private CompositeDisposable mCompositeSubscription;
    private BaseSchedulerProvider mSchedulerProvider;
    private LoanDataSource mLoanDataSource;

    ShippingAddressListPresenter(LoanDataSource loanDataSource, ShippingAddressListContract.View view, BaseSchedulerProvider schedulerProvider) {
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
    public void getConsigneeAddressList() {
        mCompositeSubscription.clear();
        mLoanDataSource.getConsigneeAddressList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.getConsigneeAddressListRequestSuccess(map);
                    }
                });
    }

    @Override
    public void deleteConsigneeAddressByIds(String ids) {
        mCompositeSubscription.clear();
        mLoanDataSource.deleteConsigneeAddressByIds(ids)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.deleteConsigneeAddressByIdsRequestSuccess(map);
                    }
                });
    }
}
