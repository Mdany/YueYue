package com.chenyu.monster.yueyue.gankio.model;

import com.chenyu.monster.yueyue.http.HttpResult;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenyu on 16/7/14.
 */
public interface GankService {
    @GET("data/{categoryName}/{count}/{pageCount}")
    Observable<Response<HttpResult<Gank>>> getGankByCountAndPage(@Path("categoryName") String category, @Path("count") int count, @Path("pageCount") int pageCount);
}
