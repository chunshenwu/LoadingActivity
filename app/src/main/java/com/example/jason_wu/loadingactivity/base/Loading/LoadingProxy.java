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
    private LoadingAsyncTask mLoadingAsyncTask;

    public LoadingProxy(final Activity activity) {
        mLoadingAsyncTask = new LoadingAsyncTask();
        mLoadingProgressDialog = new LoadingProgressDialog(activity);
        mLoadingAsyncTask.setOnListener(mLoadingProgressDialog);
        mList = new ArrayList<>();
    }

    public void startLoading() {
        mLoadingAsyncTask.startLoading();
    }

    public boolean isLoadingFinish() {
        return mLoadingAsyncTask.isLoadingFinish();
    }

    public void addCode(Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (!mList.contains(activityHashCode)) {
            mList.add(activityHashCode);
        }
    }

    public void removeCode(Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (mList.contains(activityHashCode)) {
            mList.remove(activityHashCode);
        }
    }

    public boolean isEmpty() {
        return mList.isEmpty();
    }
}
