package com.example.jason_wu.loadingactivity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.jason_wu.loadingactivity.R;
import com.example.jason_wu.loadingactivity.base.loading.LoadingProxy;

public abstract class BaseLoadingActivity extends AppCompatActivity {


    private static LoadingProxy mLoadingProxy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashing);
        if (mLoadingProxy == null) {
            mLoadingProxy = new LoadingProxy(this);
        } else {
            mLoadingProxy.addCode(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mLoadingProxy.isLoadingFinish()) {
            mLoadingProxy.startLoading();
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        if (!mLoadingProxy.isLoadingFinish()) {
            mLoadingProxy.startLoading();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingProxy != null) {
            mLoadingProxy.removeCode(this);
            if (mLoadingProxy.isEmpty()) {
                mLoadingProxy = null;
            }
        }
    }
}






