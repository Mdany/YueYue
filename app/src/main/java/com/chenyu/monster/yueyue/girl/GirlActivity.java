package com.chenyu.monster.yueyue.girl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.chenyu.monster.yueyue.R;
import com.chenyu.monster.yueyue.framework.BaseActivity;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chenyu on 16/7/21.
 */
public class GirlActivity extends BaseActivity implements GirlContract.View {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.iv_girl)
    ImageView girl;
    private static final String EXTRA_IMAGE_URL = "image_url";
    private static final String EXTRA_IAMGE_DES = "image_desc";

    private PhotoViewAttacher mPhotoViewAttacher;
    private String mImageUrl, mImageDesc;
    private boolean isHidden;

    private GirlContract.Presenter mPresenter;

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, GirlActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL, url);
        intent.putExtra(EXTRA_IAMGE_DES, desc);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.a_girl;
    }

    @Override
    protected void initView() {
        super.initView();
        new GirlPresenter(this);
    }

    @Override
    public void setUpPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(girl);
        mPhotoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                hideOrShowToolBar();
            }
        });
        mPhotoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(GirlActivity.this).setMessage("")
                        .setNegativeButton("", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("", (dialog, which) -> {
                            dialog.dismiss();
                            mPresenter.saveImageToGallery();
                        })
                        .create();
                return true;
            }
        });
    }

    @Override
    public void shareImageToOthers() {

    }

    @Override
    public void hideOrShowToolBar() {
        appBarLayout.animate()
                .translationY(isHidden ? 0 : -appBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator())
                .start();
        isHidden = !isHidden;
    }

    @Override
    public void setPresenter(GirlContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
