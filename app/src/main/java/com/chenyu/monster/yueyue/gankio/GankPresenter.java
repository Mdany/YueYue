package com.chenyu.monster.yueyue.gankio;

import com.chenyu.monster.yueyue.gankio.model.Gank;
import com.chenyu.monster.yueyue.gankio.model.GankModel;

import java.util.List;

import rx.Subscriber;

/**
 * Created by chenyu on 16/7/19.
 * Listens to User actions from the UI GankFragment,retrieves the data and updates
 * the UI as required.
 */
public class GankPresenter implements GankContract.Presenter {

    private final GankContract.View mGankView;
    private final GankModel mGankModel;

    public GankPresenter(GankContract.View mGankView) {
        if (mGankView == null)
            throw new RuntimeException("mGankView can't be null");
        this.mGankView = mGankView;
        this.mGankView.setPresenter(this);
        this.mGankModel = new GankModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void loadGankData() {
        mGankView.setProgressIndicator(true);
        mGankModel.loadGanks(new Subscriber<List<Gank>>() {
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
        mGankView.setProgressIndicator(true);
        mGankModel.loadMoreGanks(new Subscriber<List<Gank>>() {
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


}
