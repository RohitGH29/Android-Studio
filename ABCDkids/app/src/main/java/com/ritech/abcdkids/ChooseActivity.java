package com.ritech.abcdkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.Serializable;


public class ChooseActivity extends AppCompatActivity implements Serializable {

    private ImageView fir, settingbtn,sec;
    // for playing background song
    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        getSupportActionBar().hide();

        findId();


        onClick();

        // calling database
        Db.alphbet();
        Db.number();


        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }


    private void onClick() {

        fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                String strName = "an";
                intent.putExtra("a", strName);
                startActivity(intent);
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                String strName = "al";
                intent.putExtra("a", strName);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


    }

    private void findId() {
        fir = findViewById(R.id.img1);
        settingbtn = findViewById(R.id.setting);
        sec=findViewById(R.id.img2);

    }

}