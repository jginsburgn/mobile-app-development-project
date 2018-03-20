package me.itsof.a180315;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private long waitTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_splash);

        //getSupportActionBar().hide();

        TimerTask task = new TimerTask() {
            public void run() {
                finish();
                Intent principal = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(principal);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, waitTime);
    }
}
