package com.chenyu.monster.yueyue.gankio;

import com.chenyu.monster.yueyue.BasePresenter;
import com.chenyu.monster.yueyue.BaseView;
import com.chenyu.monster.yueyue.gankio.model.Gank;

import java.util.List;

/**
 * Created by chenyu on 16/7/19.
 */
public class GankContract {

    interface View extends BaseView<Presenter> {
        void showGankData(List<Gank> data);

        void addGankData(List<Gank> data);

        void setProgressIndicator(boolean isActive);

        void showLoadGankError(Throwable e);

        void showLoadGankCompleted();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadGankData();

        void loadMoreGankData();
    }
}
