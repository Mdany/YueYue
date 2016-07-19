package com.chenyu.monster.yueyue.http;

/**
 * Created by chenyu on 16/7/18.
 */

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * 统一处理http response
 *
 * @param <T>
 */
public class ResponseFunc<T> implements Func1<Response<T>, Observable<T>> {
    @Override
    public Observable<T> call(Response<T> response) {
        if (response.isSuccessful()) {
            return Observable.just(response.body());
        }
        switch (response.code()) {
            default:
                return Observable.error(new ApiException(100));
        }
    }
}
