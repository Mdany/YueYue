package com.chenyu.monster.yueyue.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenyu on 16/2/4.
 */
public abstract class BaseFragment extends Fragment {
    protected int rootLayoutID;
    protected View rootView;
    protected Activity mActivity;
    protected SparseArray<View> mViews;

    public BaseFragment(int rootLayoutID) {
        super();
        this.rootLayoutID = rootLayoutID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(rootLayoutID, container, false);
        mActivity = getActivity();
        viewDidLoad();
        return rootView;
    }

    public abstract void viewDidLoad();

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     * @param viewId
     * @param <E>
     * @return
     */
    protected <E extends View> E findView(int viewId) {
        if (rootView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) rootView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }
}
