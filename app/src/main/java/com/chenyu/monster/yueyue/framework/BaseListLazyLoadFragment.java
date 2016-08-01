package com.chenyu.monster.yueyue.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenyu.monster.yueyue.widget.func.SwipeRefreshLayer;

/**
 * Created by chenyu on 16/2/4.
 * <p>
 * 含有recyclerView的fragment
 * LayoutManager处理视图位置，ItemAnimator处理动画。ViewHolder是最后的部分：它的职责是处理发生在RecyclerView中特定item的事件。
 * 实现懒加载
 */
public abstract class BaseListLazyLoadFragment<A extends BaseListAdapter
        , L extends RecyclerView.LayoutManager
        , I extends RecyclerView.ItemAnimator> extends BaseListFragment<A, L, I>
        implements SwipeRefreshLayout.OnRefreshListener
        , SwipeRefreshLayer {

    //**********懒加载**********//
    private boolean isFirstLoad;//fragment是否第一次加载
    private boolean isVisible;//fragment是否可见
    private boolean isInitView;//fragment是否与view建立关联关系

    public BaseListLazyLoadFragment(int rootLayoutId, int swipeLayoutId, int recyclerId) {
        super(rootLayoutId, swipeLayoutId, recyclerId);
        this.isFirstLoad = true;
        this.isVisible = false;
        this.isInitView = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = super.onCreateView(inflater, container, savedInstanceState);
        isInitView = true;
        lazyLoadData();
        return convertView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoadData() {
        //非第一次加载||不可见||未关联view 都不加载数据
        if (!isFirstLoad || !isInitView || !isVisible) {
            return;
        }
        refreshData();
        isFirstLoad = false;
    }
}