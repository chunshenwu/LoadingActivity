package com.example.jason_wu.loadingactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Main2Activity extends BaseLoadingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, Main2Activity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startIntent;
    }

}


