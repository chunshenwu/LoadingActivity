package com.example.jason_wu.loadingactivity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jason_wu.loadingactivity.base.BaseLoadingActivity;
import com.example.jason_wu.loadingactivity.R;

public class MainActivity extends BaseLoadingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Main2Activity.getStartIntent(getApplicationContext()));
                finish();
            }
        });

        //TEST branch 1
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
        Intent startIntent = new Intent(context, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startIntent;
    }

}


