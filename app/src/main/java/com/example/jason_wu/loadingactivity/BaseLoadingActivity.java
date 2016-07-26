package com.example.jason_wu.loadingactivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

public abstract class BaseLoadingActivity extends AppCompatActivity {


    private static LoadingCenter mLoadingCenter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashing);
        if (mLoadingCenter == null) {
            mLoadingCenter = new LoadingCenter(this);
        } else {
            mLoadingCenter.addCode(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mLoadingCenter.isLoadingFinish()) {
            mLoadingCenter.startLoading();
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        if (!mLoadingCenter.isLoadingFinish()) {
            mLoadingCenter.startLoading();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingCenter != null) {
            mLoadingCenter.removeCode(this);
            if (mLoadingCenter.isEmpty()) {
                mLoadingCenter = null;
            }
        }
    }
}

class LoadingCenter {

    private ArrayList<Integer> mList;
    private ProgressDialog mProgressDialog;
    private LoadingAsyncTask asyncTask;

    LoadingCenter(final Activity activity) {
        mProgressDialog = new ProgressDialog(activity);
        asyncTask = new LoadingAsyncTask();
        asyncTask.setProgressDialog(mProgressDialog);
        mList = new ArrayList();
    }

    public void startLoading() {
        asyncTask.startLoading();
    }

    boolean isLoadingFinish() {
        return asyncTask.isLoadingFinish();
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

 final class LoadingAsyncTask extends AsyncTask<URL, Integer, Boolean> {

    private static final String TAG = LoadingAsyncTask.class.getSimpleName();

    enum Status {
        NONE,
        RUNNING,
        FINISHED,
        ERROR,
        ;
    }

    //View
    private ProgressDialog mProgressDialog = null;
    private Status mStatus = null;

    public LoadingAsyncTask() {
        mStatus = Status.NONE;
    }

     void setProgressDialog(final ProgressDialog progressDialog ) {
         mProgressDialog = progressDialog;
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
         mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         mProgressDialog.setMax(10000);
         mProgressDialog.setMessage("Loading...");
         mProgressDialog.setCancelable(false);
         mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "好", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         mProgressDialog.show();
         mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
     }

     @Override
    protected Boolean doInBackground(URL... urls) {
        mStatus = Status.RUNNING;
        for (int i = 0 ; i < 10000; i++) {
            this.publishProgress(i);
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        final int i = values[0];
        mProgressDialog.setProgress(i);
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        mStatus = (bool ? Status.FINISHED : Status.ERROR);
        mProgressDialog.setProgress(10000);
        mProgressDialog.setMessage("Loading...OK");
        mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
    }

}






