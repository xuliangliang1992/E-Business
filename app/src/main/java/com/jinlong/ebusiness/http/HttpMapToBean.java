package com.jinlong.ebusiness.http;


import com.jinlong.ebusiness.http.response.BaseResponse;

import io.reactivex.functions.Function;

/**
 * @author xll
 * @date 2018/9/20
 */

public class HttpMapToBean<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) throws Exception {
        return tBaseResponse.getData();
    }
}
