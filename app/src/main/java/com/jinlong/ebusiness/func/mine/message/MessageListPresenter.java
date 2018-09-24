package com.jinlong.ebusiness.func.mine.message;

import com.jinlong.ebusiness.http.request.LoanDataSource;
import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;
import com.jinlong.ebusiness.http.subscriber.SaicObserver;
import com.xll.mvplib.schedulers.BaseSchedulerProvider;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author xll
 * @date 2018/9/24
 */

public class MessageListPresenter implements MessageListContract.Presenter {

    private MessageListContract.View mView;
    private CompositeDisposable mCompositeSubscription;
    private BaseSchedulerProvider mSchedulerProvider;
    private LoanDataSource mLoanDataSource;

    MessageListPresenter(LoanDataSource loanDataSource, MessageListContract.View view, BaseSchedulerProvider schedulerProvider) {
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
    public void getMessageList(int type, int page, int rows) {
        mCompositeSubscription.clear();
        mLoanDataSource.getMessageList(type, page, rows)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<BaseResponse<MessageListBean>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(BaseResponse<MessageListBean> messageListBean) {
                        mView.getMessageListRequestSuccess(messageListBean);
                    }
                });
    }

    @Override
    public void readMessageByIds(String ids) {
        mCompositeSubscription.clear();
        mLoanDataSource.readMessageByIds(ids)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.readMessageByIdsRequestSuccess(map);
                    }
                });
    }

    @Override
    public void deleteMessageByIds(String ids) {
        mCompositeSubscription.clear();
        mLoanDataSource.deleteMessageByIds(ids)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SaicObserver<Map<String, Object>>(mView) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(Map<String, Object> map) {
                        mView.deleteMessageByIdsRequestSuccess(map);
                    }
                });
    }
}
