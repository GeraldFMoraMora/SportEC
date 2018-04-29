package com.sportec.sportec.gui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.sportec.sportec.R;

public class SplashActivity extends AppCompatActivity {

    private final static int mSplashDelay = 5000;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.splash_screen);


    }
}
