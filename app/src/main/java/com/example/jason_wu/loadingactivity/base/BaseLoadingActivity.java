package com.example.jason_wu.loadingactivity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jason_wu.loadingactivity.R;
import com.example.jason_wu.loadingactivity.base.loading.LoadingProxy;

public abstract class BaseLoadingActivity extends AppCompatActivity {


    private static LoadingProxy mLoadingProxy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashing);
        initLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoading();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        checkLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLoading();
    }

    private void initLoading() {
        if (mLoadingProxy == null) {
            mLoadingProxy = new LoadingProxy(this);
        } else {
            mLoadingProxy.addCode(this);
        }
    }

    private void checkLoading() {
        if (!mLoadingProxy.isLoadingFinish()) {
            mLoadingProxy.startLoading();
        }
    }

    private void destroyLoading() {
        if (mLoadingProxy != null) {
            mLoadingProxy.removeCode(this);
            if (mLoadingProxy.isEmpty()) {
                mLoadingProxy = null;
            }
        }
    }
}






