package com.study.newspadhlo;

import static java.util.Objects.requireNonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Objects;

public class Splash extends AppCompatActivity {


        final static int SPLASH_TIMER=2000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            // For hide Action Bar
           // requireNonNull(getSupportActionBar()).hide();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_TIMER);



        }
    }