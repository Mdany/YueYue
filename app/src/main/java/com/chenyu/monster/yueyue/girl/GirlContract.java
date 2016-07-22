package com.chenyu.monster.yueyue.girl;

import com.chenyu.monster.yueyue.BasePresenter;
import com.chenyu.monster.yueyue.BaseView;

/**
 * Created by chenyu on 16/7/21.
 */
public class GirlContract {
    public interface View extends BaseView<Presenter> {
        void setUpPhotoAttacher();
        void shareImageToOthers();
        void hideOrShowToolBar();
    }

    public interface Presenter extends BasePresenter {
        void saveImageToGallery();
    }
}
