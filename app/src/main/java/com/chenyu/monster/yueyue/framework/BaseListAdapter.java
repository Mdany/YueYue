package com.chenyu.monster.yueyue.framework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/2/4.
 */
public abstract class BaseListAdapter<M extends Entity, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    protected Context mContext;
    protected List<M> data;
    /**
     * 是否显示加载更多
     */
    private boolean isShowFooter;

    public BaseListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BaseListAdapter(Context mContext, List<M> data) {
        this.mContext = mContext;
        this.data = data;
        isShowFooter = true;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isShowFooter) {
            return TYPE_ITEM;
        } else {
            if (position == getItemCount() - 1)
                return TYPE_FOOTER;
            else
                return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        int footCount = isShowFooter ? 1 : 0;
        if (data != null) {
            return data.size() + footCount;
        } else {
            return footCount;
        }
    }

    public boolean isShowFooter() {
        return isShowFooter;
    }

    public void setShowFooter(boolean showFooter) {
        isShowFooter = showFooter;
    }

    /**
     * 添加item
     *
     * @param item
     */
    public void addItem(M item) {
        if (data == null)
            data = new ArrayList<>();
        data.add(0, item);
        notifyItemInserted(0);
    }

    /**
     * 批量添加items
     *
     * @param items
     */
    public void addItems(List<M> items) {
        if (data == null)
            data = new ArrayList<>();
        int start = data.size();
        data.addAll(items);
        notifyItemRangeInserted(start, items.size());
    }

    /**
     * 移除指定item
     *
     * @param position
     */
    public void removeItem(int position) {
        if (data != null) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 填充数据
     *
     * @param data
     */
    public void setData(List<M> data) {
        if (this.data != null) {
            this.data.clear();
        }
        this.data = data;
        notifyDataSetChanged();
    }
}
