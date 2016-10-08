package com.chenyu.monster.yueyue.city.model;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chenyu on 16/9/21.
 */

public interface CityService {
    @GET("list/?key=MDGBZ-BDDWP-4VCDV-LFSOE-TDOEV-TWF4Z")
    Observable<Response<HttpResult<City>>> getCities();
}
