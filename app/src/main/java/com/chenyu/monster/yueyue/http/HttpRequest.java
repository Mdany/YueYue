package com.chenyu.monster.yueyue.http;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenyu on 16/4/26.
 */
public class HttpRequest {
    private final int TIME_OUT = 10;
    public Retrofit retrofit;
//    private static HttpRequest httpRequest;

    private HttpRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Urls.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                .serializeNulls()
                                .create()))
                .build();
    }

    public static HttpRequest getInstance() {
//        if (httpRequest == null) {
//            httpRequest = new HttpRequest();
//        }
//        return httpRequest;
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取top250的电影
     *
     * @param subscriber 类似callback，其实是个观察者对象，根据请求的结果回调不同方法
     * @param start      开始位置
     * @param count      数目
     */
//    public void getTopMovie(int start, int count, Subscriber<Subject> subscriber) {
//        MovieService movieService = retrofit.create(MovieService.class);
//        movieService.getTopMovie(start, count)
//                .flatMap(new ResponseFunc<HttpResult<List<Subject>>>())
//                .flatMap(new Func1<HttpResult<List<Subject>>, Observable<List<Subject>>>() {
//                    @Override
//                    public Observable<List<Subject>> call(HttpResult<List<Subject>> httpResult) {//model统一继承entity，这里就可以抽成一个类
//                        if (httpResult.getCount() != 0) {
//                            return Observable.error(new ApiException(100));
//                        } else {
//                            return Observable.just(httpResult.getSubjects());
//                        }
//                    }
//                })
//                .flatMap(new Func1<List<Subject>, Observable<Subject>>() {
//                    @Override
//                    public Observable<Subject> call(List<Subject> subjects) {
//                        return Observable.from(subjects);
//                    }
//                })
//                .compose(ScheduleCompat.<Subject>applyIoSchedulers())
//                .subscribe(subscriber);
//    }

    /**
     * 单例模式,双检查
     */
    private static class InstanceHolder{
        static HttpRequest INSTANCE = new HttpRequest();
    }
}
