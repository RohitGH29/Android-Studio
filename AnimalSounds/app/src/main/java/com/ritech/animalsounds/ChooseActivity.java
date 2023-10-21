package com.ritech.animalsounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.analytics.FirebaseAnalytics;


import java.io.Serializable;


public class ChooseActivity extends AppCompatActivity implements Serializable {

    private ImageView animal, letter, number, transport,fruit,vegitable, settingBtn;

    boolean DoublePressToExit = false;
    MediaPlayer mediaPlayer;

    LottieAnimationView anim;  // for celebration animation effect

    public static int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


       findId();

        // background music
        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        mediaPlayer.start();



        onClick();

        // calling database
        db();


      //  anim.playAnimation();


        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }

    private void db() {
        Db.animalArrayList.clear();
        Db.alphabetsArrayList.clear();
        Db.numberArrayList.clear();
        Db.vehicleArrayList.clear();
        Db.fruitArrayList.clear();
        Db.vegitableArrayList.clear();
        Db.animal();
        Db.alphabets();
        Db.number();
        Db.vehicle();
        Db.fruits();
        Db.vegetable();
    }


    private void onClick() {
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("an");
            }
        });
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("le");
            }
        });

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("no");
            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("tr");
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("fr");
            }
        });
        vegitable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("ve");
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    // go from chose activity to main activity
    private void intent(String string) {

        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        String strName = string;
        intent.putExtra("a", strName);
        mediaPlayer.pause();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void findId() {
        animal = findViewById(R.id.ani);
        letter = findViewById(R.id.let);
        number = findViewById(R.id.num);
        transport = findViewById(R.id.tra);
        fruit = findViewById(R.id.fru);
        vegitable=findViewById(R.id.veg);
        settingBtn = findViewById(R.id.setting);
        anim = findViewById(R.id.celebration);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
        mediaPlayer.stop();
    }


    @Override
    public void onBackPressed() {
        if (DoublePressToExit) {
            finishAffinity();
        } else {
            DoublePressToExit = true;
            Toast.makeText(this, "Press again", Toast.LENGTH_LONG).show();
        }
    }
}