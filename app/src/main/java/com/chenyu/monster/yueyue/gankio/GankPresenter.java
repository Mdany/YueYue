package com.chenyu.monster.yueyue.gankio;

import com.chenyu.monster.yueyue.gankio.model.Gank;
import com.chenyu.monster.yueyue.gankio.model.GankService;
import com.chenyu.monster.yueyue.http.HttpRequest;
import com.chenyu.monster.yueyue.http.HttpResult;
import com.chenyu.monster.yueyue.http.HttpResultFunc;
import com.chenyu.monster.yueyue.http.ResponseFunc;
import com.chenyu.monster.yueyue.http.ScheduleCompat;
import com.chenyu.monster.yueyue.http.Urls;

import java.util.List;

import rx.Subscriber;

/**
 * Created by chenyu on 16/7/19.
 * Listens to User actions from the UI GankFragment,retrieves the data and updates
 * the UI as required.
 */
public class GankPresenter implements GankContract.Presenter {
    /**
     * 起始页码
     */
    private int page = 1;
    /**
     * 数量
     */
    private int count = 10;

    private final GankContract.View mGankView;

    public GankPresenter(GankContract.View mGankView) {
        if (mGankView == null)
            throw new RuntimeException("mGankView can't be null");
        this.mGankView = mGankView;
        this.mGankView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadGankData() {
        loadGanks(new Subscriber<List<Gank>>() {
            @Override
            public void onCompleted() {
                mGankView.showLoadGankCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mGankView.showLoadGankError(e);
            }

            @Override
            public void onNext(List<Gank> ganks) {
                mGankView.showGankData(ganks);
            }
        });
    }

    /**
     * 加载更多福利
     */
    @Override
    public void loadMoreGankData() {
        ++page;
        loadGanks(new Subscriber<List<Gank>>() {
            @Override
            public void onCompleted() {
                mGankView.showLoadGankCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mGankView.showLoadGankError(e);
            }

            @Override
            public void onNext(List<Gank> ganks) {
                mGankView.addGankData(ganks);
            }
        });
    }

    /**
     * 加载福利数据
     */
    private void loadGanks(Subscriber<List<Gank>> gankSubscriber) {
        GankService gankService = HttpRequest.getInstance().retrofit.create(GankService.class);
        gankService.getGankByCountAndPage(Urls.GANK_CATEGORY_WELFARE, 10, page)
                .flatMap(new ResponseFunc<HttpResult<Gank>>())
                .flatMap(new HttpResultFunc<Gank>())
                .compose(ScheduleCompat.<List<Gank>>applyIoSchedulers())
                .subscribe(gankSubscriber);
    }
}
