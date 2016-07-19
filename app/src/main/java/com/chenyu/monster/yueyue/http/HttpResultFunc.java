package com.chenyu.monster.yueyue.http;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chenyu on 16/7/18.
 * api请求结果结构统一处理
 */
public class HttpResultFunc<T extends Entity> implements Func1<HttpResult<T>, Observable<List<T>>> {
    @Override
    public Observable<List<T>> call(HttpResult<T> tHttpResult) {
        if (tHttpResult.error){
            return Observable.error(new ApiException(100));
        }else{
            return Observable.just(tHttpResult.results);
        }
    }
}
