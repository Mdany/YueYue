package com.chenyu.monster.yueyue.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenyu on 16/8/12.
 */
public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Headers headers = Headers.of(getHeaders());
        Request.Builder builder = original
                .newBuilder()
                .method(original.method(),original.body())
                .headers(headers);

        return chain.proceed(builder.build());
    }

    /**
     * 返回Header参数
     * @return
     */
    private Map<String ,String> getHeaders(){
        return new HashMap<>();
    }
}
