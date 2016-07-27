package com.example.jason_wu.loadingactivity.base.loading;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

/**
 * Created by jason_wu on 7/27/16.
 */
final class LoadingAsyncTask extends AsyncTask<URL, Integer, Boolean> {

    private static final String TAG = LoadingAsyncTask.class.getSimpleName();

    interface OnListener {
        void onPreExecute();
        void onProgressUpdate(Integer val);
        void onPostExecute(Boolean bool);
    }

    enum Status {
        NONE,
        RUNNING,
        FINISHED,
        ERROR,;
    }

    private Status mStatus = null;
    private OnListener mOnListener = null;

    public LoadingAsyncTask() {
        mStatus = Status.NONE;

    }

    void setOnListener(final OnListener onListener) {
        mOnListener = onListener;
    }

    boolean isLoadingFinish() {
        return mStatus == Status.FINISHED;
    }

    void startLoading() {
        if (isLoadingFinish()) {
            Log.i(TAG, "startLoading: isLoadingFinish is true, return.");
            return;
        }

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mOnListener == null) {
            return;
        }
        mOnListener.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(URL... urls) {
        mStatus = Status.RUNNING;
        for (int i = 0; i < 10000; i++) {
            this.publishProgress(i);
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mOnListener == null || values == null) {
            return;
        }

        for (Integer val : values) {
            mOnListener.onProgressUpdate(val);
        }
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        mStatus = (bool ? Status.FINISHED : Status.ERROR);
        mOnListener.onPostExecute(bool);
    }

}
