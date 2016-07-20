package com.chenyu.monster.yueyue.widget.func;

import com.chenyu.monster.yueyue.widget.MultiSwipeRefreshLayout;

/**
 * Created by chenyu on 16/7/20.
 */
public interface SwipeRefreshLayer {
    void requestDataRefresh();
    void setRefresh(boolean refresh);
    void setProgressViewOffset(boolean scale, int start,int end);
    void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback);
}
