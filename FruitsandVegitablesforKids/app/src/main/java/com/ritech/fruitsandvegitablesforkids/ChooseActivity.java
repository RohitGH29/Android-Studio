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

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.Serializable;


public class ChooseActivity extends AppCompatActivity implements Serializable {

    private ImageView animal, letter, number, transport,fruit,vegitable,back;

    boolean DoublePressToExit = false;
  //  MediaPlayer mediaPlayer;

    public static int check=0;

   // LottieAnimationView anim;  // for celebration animation effect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


       findId();

        // background music
//        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
//        mediaPlayer.start();



        onClick();

        // calling database
        db();


       // anim.playAnimation();


        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }

    private void db() {
        Db.animalArrayList.clear();
        Db.alphabetsArrayList.clear();
        Db.numberArrayList.clear();
        Db.vehicleArrayList.clear();
        Db.fruitArrayList.clear();
        Db.vegetableArrayList.clear();
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

    }

    // go from chose activity to main activity
    private void intent(String string) {

        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        String strName = string;
        intent.putExtra("a", strName);
      //  mediaPlayer.pause();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void findId() {
        animal = findViewById(R.id.an);
        letter = findViewById(R.id.al);
        number = findViewById(R.id.nu);
        transport = findViewById(R.id.tr);
        fruit = findViewById(R.id.fr);
        vegitable=findViewById(R.id.ve);
        back=findViewById(R.id.backBtn);
       // anim = findViewById(R.id.celebration);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  mediaPlayer.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
      //  mediaPlayer.stop();
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