package com.ritech.fruitsandvegitablesforkids;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;


public class StartActivity extends AppCompatActivity {
    ImageView settingBtn,playBtn,soundBtn,noSoundBtn;
    boolean DoublePressToExit = false;


   public static  MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findId();

        onClick();

        // calling database
        db();


        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // background music
      //  mediaPlayer.reset();


            mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);

    }

    private void findId() {
        settingBtn = findViewById(R.id.setting);
        playBtn=findViewById(R.id.play);
        soundBtn=findViewById(R.id.sound);
        noSoundBtn=findViewById(R.id.noSound);
    }
    private void onClick() {

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ChooseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundBtn.setVisibility(View.INVISIBLE);
                noSoundBtn.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
            }
        });

        noSoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noSoundBtn.setVisibility(View.INVISIBLE);
                soundBtn.setVisibility(View.VISIBLE);
                mediaPlayer.start();
            }
        });

    }

    private void db() {
        Db.fruitArrayList.clear();
        Db.vegetableArrayList.clear();
        Db.fruits();
        Db.vegetable();
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