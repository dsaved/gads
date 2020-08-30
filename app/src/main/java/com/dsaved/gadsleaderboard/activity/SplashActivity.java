package com.dsaved.gadsleaderboard.activity;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.dsaved.gadsleaderboard.utils.Navigator;
import com.dsaved.gadsleaderboard.R;

public class SplashActivity extends AppCompatActivity {

    Navigator navigator = new Navigator(SplashActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigator.to(MainActivity.class).open(true);
            }
        }, 2000);
    }
}
