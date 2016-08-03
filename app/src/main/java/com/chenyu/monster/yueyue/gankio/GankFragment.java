package com.chenyu.monster.yueyue.gankio;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chenyu.monster.yueyue.R;
import com.chenyu.monster.yueyue.framework.BaseListFragment;
import com.chenyu.monster.yueyue.gankio.func.OnGirlTouchListener;
import com.chenyu.monster.yueyue.gankio.model.Gank;
import com.chenyu.monster.yueyue.girl.GirlActivity;

import java.util.List;

/**
 * Created by chenyu on 16/7/14.
 */
public class GankFragment extends BaseListFragment<GankAdapter
        , StaggeredGridLayoutManager
        , DefaultItemAnimator>
        implements GankContract.View
        , OnGirlTouchListener {
    private GankContract.Presenter mPresenter;

    public static GankFragment newInstance() {
        return new GankFragment();
    }

    public GankFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    @Override
    protected GankAdapter getAdapter() {
        return new GankAdapter(mActivity);
    }

    @Override
    protected StaggeredGridLayoutManager getManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected DefaultItemAnimator getItemAnimator() {
        return new DefaultItemAnimator();
    }

    @Override
    protected void refreshData() {
        mPresenter.loadGankData();
    }

    @Override
    protected void loadMoreData() {
        mPresenter.loadMoreGankData();
    }


    @Override
    public void viewDidLoad() {
        adapter.setOnGirlTouchListener(this);
    }

    @Override
    public void showGankData(List<Gank> data) {
        adapter.setData(data);
    }

    @Override
    public void addGankData(List<Gank> data) {
        adapter.addItems(data);
    }

    @Override
    public void showLoadGankError(Throwable e) {
        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadGankCompleted() {
        setRefresh(false);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setProgressIndicator(boolean isActive) {
        setRefresh(isActive);
    }

    @Override
    public void setPresenter(GankContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onTouch(View v, View avatar, View card, Gank girl) {
        startActivity(GirlActivity.newIntent(mActivity, girl.url, girl.desc));
    }
}
