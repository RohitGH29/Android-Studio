package com.ritech.quizkarlo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.Login.LoginActivity;
import com.ritech.quizkarlo.OnbordingScreen.IntroActivity;

import java.util.Objects;


public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();


        // This is use to check for existing users
        mAuth = FirebaseAuth.getInstance();

        // Access a Cloud Firestore instance from your Activity
        DbQuery.g_firestore = FirebaseFirestore.getInstance();


        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mAuth.getCurrentUser() != null) {
                    DbQuery.loadData(new MyCompleteListener() {
                        @Override
                        public void onSuccess() {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                        }

                        @Override
                        public void onFailure() {
                            Toast.makeText(SplashActivity.this, "Somethig Went Wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    // this is for check condition if user come for first time onBoarding screen show , else not show
                    onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                    boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                    if (isFirstTime) {

                        SharedPreferences.Editor editor = onBoardingScreen.edit();
                        editor.putBoolean("firstTime", false);
                        editor.commit();

                        Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();

                    } else {

                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                }


            }

        }.start();

    }
}