package com.chenyu.monster.yueyue.girl;

/**
 * Created by chenyu on 16/7/21.
 */
public class GirlPresenter implements GirlContract.Presenter {
    private GirlContract.View mGirlView;

    public GirlPresenter(GirlContract.View mGirlView) {
        this.mGirlView = mGirlView;
        this.mGirlView.setPresenter(this);
    }

    @Override
    public void saveImageToGallery() {

    }

    @Override
    public void start() {

    }
}
