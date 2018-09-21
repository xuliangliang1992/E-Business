package com.jinlong.ebusiness.injection;


import com.jinlong.ebusiness.http.request.LoanRepository;
import com.jinlong.ebusiness.http.request.RemoteLoanDataSource;
import com.xll.mvplib.schedulers.BaseSchedulerProvider;
import com.xll.mvplib.schedulers.SchedulerProvider;

/**
 * @author xll
 * @date 2018/9/17
 */
public class Injection {

    public static LoanRepository provideLoanRepository() {
        return LoanRepository.getInstance(RemoteLoanDataSource.getInstance());
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
