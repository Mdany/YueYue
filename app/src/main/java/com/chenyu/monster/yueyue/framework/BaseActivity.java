package com.chenyu.monster.yueyue.framework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by chenyu on 16/1/26.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        initView();
    }

    protected void initView(){
        ButterKnife.bind(this);
    }

    public void startActivity(Class<? extends BaseActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void startActivity(Class<? extends BaseActivity> activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class<? extends BaseActivity> activityClass, Uri data) {
        Intent intent = new Intent(this, activityClass);
        intent.setData(data);
        startActivity(intent);
    }

    public void startActivity(Class<? extends BaseActivity> activityClass, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        startActivityForResult(intent, requestCode);
    }

    public void startActivity(Class<? extends BaseActivity> activityClass, Uri data, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        intent.setData(data);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
