package com.example.jason_wu.loadingactivity.base.loading;

import android.app.Activity;

import com.example.jason_wu.loadingactivity.base.BaseLoadingActivity;

import java.util.ArrayList;

/**
 * Created by jason_wu on 7/27/16.
 */
public class LoadingProxy {

    private ArrayList<Integer> mList;
    private LoadingProgressDialog mLoadingProgressDialog;
    private LoadingAsyncTask mmLoadingAsyncTask;

    public LoadingProxy(final Activity activity) {
        mmLoadingAsyncTask = new LoadingAsyncTask();
        mLoadingProgressDialog = new LoadingProgressDialog(activity);
        mmLoadingAsyncTask.setOnListener(mLoadingProgressDialog);
        mList = new ArrayList<>();
    }

    public void startLoading() {
        mmLoadingAsyncTask.startLoading();
    }

    public boolean isLoadingFinish() {
        return mmLoadingAsyncTask.isLoadingFinish();
    }

    public void addCode(BaseLoadingActivity baseLoadingActivity) {
        final Integer activityHashCode = baseLoadingActivity.hashCode();
        if (!mList.contains(activityHashCode)) {
            mList.add(activityHashCode);
        }
    }

    public void removeCode(BaseLoadingActivity baseLoadingActivity) {
        final Integer activityHashCode = baseLoadingActivity.hashCode();
        if (mList.contains(activityHashCode)) {
            mList.remove(activityHashCode);
        }
    }

    public boolean isEmpty() {
        return mList.isEmpty();
    }
}
