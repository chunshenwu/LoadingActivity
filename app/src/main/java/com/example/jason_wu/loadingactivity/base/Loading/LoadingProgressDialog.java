package com.example.jason_wu.loadingactivity.base.loading;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by jason_wu on 7/27/16.
 */
class LoadingProgressDialog extends ProgressDialog implements LoadingAsyncTask.OnListener{
    public LoadingProgressDialog(Context context) {
        super(context);
    }

    public LoadingProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void onPreExecute() {
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setMax(10000);
        setMessage("Loading...");
        setCancelable(false);
        setButton(DialogInterface.BUTTON_POSITIVE, "好", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        show();
        getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    public void onProgressUpdate(Integer val) {
        setProgress(val);
}

    @Override
    public void onPostExecute(Boolean bool) {
        setProgress(10000);
        setMessage("Loading...OK");
        getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);

    }
}
