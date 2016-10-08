package com.chenyu.monster.yueyue.city.model;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chenyu on 16/9/21.
 */

public class HttpResultFunc<T extends Entity> implements Func1<HttpResult<T>, Observable<List<List<T>>>> {
    @Override
    public Observable<List<List<T>>> call(HttpResult<T> tHttpResult) {
        return Observable.just(tHttpResult.result);
    }
}
