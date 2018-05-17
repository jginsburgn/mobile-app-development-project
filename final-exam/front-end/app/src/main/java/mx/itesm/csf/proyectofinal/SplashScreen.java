package mx.itesm.csf.proyectofinal;



import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import gr.net.maroulis.library.EasySplashScreen;
import mx.itesm.csf.proyectofinal.Controller.Services;

public class SplashScreen extends AppCompatActivity {

    final Random rnd = new Random();
    String splashName;                                  /* Variable to access the image or video for the splash */
    int drawableId;
    String TAG = "SPLASH";
    String splash_url;

    final int SPLASH_NUM = 5;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View easySplashScreenView = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(VideoSplash.class)
                .withSplashTimeOut(2000)
                .withBackgroundResource(android.R.color.black)
                .withLogo(R.drawable.logo)
                .create();

        setContentView(easySplashScreenView);
    }*/
}
