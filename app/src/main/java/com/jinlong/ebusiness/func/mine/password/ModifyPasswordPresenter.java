package com.jinlong.ebusiness.func.mine.password;

import com.jinlong.ebusiness.http.request.LoanDataSource;
import com.jinlong.ebusiness.http.subscriber.SaicObserver;
import com.xll.mvplib.schedulers.BaseSchedulerProvider;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author xll
 * @date 2018/9/21
 */

public class ModifyPasswordPresenter implements ModifyPasswordContract.Presenter {

    private ModifyPasswordContract.View mView;
    private CompositeDisposable mCompositeSubscription;
    private BaseSchedulerProvider mSchedulerProvider;
    private LoanDataSource mLoanDataSource;

    ModifyPasswordPresenter(LoanDataSource loanDataSource, ModifyPasswordContract.View view, BaseSchedulerProvider schedulerProvider) {
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
    public void modifyPwd(String opwd, String npwd) {
        mCompositeSubscription.clear();
        mLoanDataSource.modifyPwd(opwd, npwd)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.modifyPwdRequestSuccess(map);
                    }
                });
    }
}
