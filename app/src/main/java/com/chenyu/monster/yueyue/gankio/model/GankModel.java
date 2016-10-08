package com.chenyu.monster.yueyue.gankio.model;

import com.chenyu.monster.yueyue.http.HttpRequest;
import com.chenyu.monster.yueyue.http.HttpResult;
import com.chenyu.monster.yueyue.http.HttpResultFunc;
import com.chenyu.monster.yueyue.http.ResponseFunc;
import com.chenyu.monster.yueyue.http.ScheduleCompat;
import com.chenyu.monster.yueyue.http.Urls;

import java.util.List;

import rx.Subscriber;

/**
 * Created by chenyu on 16/7/21.
 * model
 */
public class GankModel {
    /**
     * 起始页码
     */
    private int page = 1;
    /**
     * 数量
     */
    private int count = 10;

    private GankService gankService;

    public GankModel() {
        gankService = HttpRequest.getInstance().retrofit.create(GankService.class);
    }

    /**
     * 加载福利数据
     */
    public void loadGanks(Subscriber<List<Gank>> gankSubscriber) {
        gankService.getGankByCountAndPage(Urls.GANK_CATEGORY_WELFARE, count, page)
                .flatMap(new ResponseFunc<HttpResult<Gank>>())
                .flatMap(new HttpResultFunc<Gank>())
                .compose(ScheduleCompat.<List<Gank>>applyIoSchedulers())
                .subscribe(gankSubscriber);
    }

    /**
     * 加载更多福利数据
     */
    public void loadMoreGanks(Subscriber<List<Gank>> gankSubscriber) {
        ++page;
        loadGanks(gankSubscriber);
    }
}
