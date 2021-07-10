package com.example.attenda_attempt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //after the splashscreen goes away, this prevents the user from returning back to the splashscreen if the user presses the back button
        Runnable runnable=new Runnable() {
            @Override
            public void run(){
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };
        //plays the splashscreen for 5000ms (2.5 seconds)
        Handler handler=new Handler();
        handler.postDelayed(runnable, 2500);
    }
}