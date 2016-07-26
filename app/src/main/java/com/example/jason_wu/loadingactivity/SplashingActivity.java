package com.example.jason_wu.loadingactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashing);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.getStartIntent(getApplicationContext()));
                finish();
            }
        });

    }
}

