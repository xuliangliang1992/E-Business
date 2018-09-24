package com.jinlong.ebusiness.http;

import com.jinlong.ebusiness.base.MainApplication;
import com.jinlong.ebusiness.constant.Constant;
import com.xll.mvplib.utils.SharePreferenceUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xll
 * @date 2018/9/20
 */

public class AppRetrofit {

    private final Retrofit retrofit;

    private static int CONNECT_TIME_OUT = 10;
    private int READ_TIME_OUT = 120;

    public AppRetrofit() {
        retrofit = new Retrofit.Builder().client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    public AppRetrofit(boolean hasToken) {

        if (!hasToken) {
            retrofit = new Retrofit.Builder().client(initNoTokenBuilder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build();
        } else {
            retrofit = new Retrofit.Builder().client(initBuilder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build();
        }
    }

    public AppRetrofit(int connectTimeOut) {
        CONNECT_TIME_OUT = connectTimeOut;
        retrofit = new Retrofit.Builder().client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    private OkHttpClient.Builder initBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token = (String) SharePreferenceUtil.getInstance().get(MainApplication.getInstance().getApplicationContext(),
                                Constant.SHARED_PREFERENCE_FILE_NAME, SharePreferenceUtil.TOKEN, "");
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "edc4796d-1aa5-4178-9c24-c653ebfd21c4")
                                .build();
                        return chain.proceed(newRequest);
                    }
                });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level(Constant.IS_DEBUG));
        builder.addInterceptor(interceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        return builder;
    }

    private OkHttpClient.Builder initNoTokenBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .build();
                        return chain.proceed(newRequest);
                    }
                });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level(Constant.IS_DEBUG));
        builder.addInterceptor(interceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        return builder;
    }

    private HttpLoggingInterceptor.Level level(boolean isDebug) {
        return isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    }

    public FaceIDService getFaceIDService() {
        return retrofit.create(FaceIDService.class);
    }

}
