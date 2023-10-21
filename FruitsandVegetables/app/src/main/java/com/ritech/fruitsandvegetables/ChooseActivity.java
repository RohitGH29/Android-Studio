package com.ritech.fruitsandvegetables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.Serializable;


public class ChooseActivity extends AppCompatActivity implements Serializable {

    private ImageView fir, sec,back;



   // LottieAnimationView anim;  // for celebration animation effect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        findId();

        onClick();

        //MediaPlayer.create(this, R.raw.bgmusic);
      //  StartActivity.mediaPlayer.start();

     //   anim.playAnimation();

    }

    private void onClick() {
        fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("fruit");
            }
        });
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent("vegitable");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });


    }

    // go from chose activity to main activity
    private void intent(String string) {

        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        String strName = string;
        intent.putExtra("a", strName);
        startActivity(intent);
     //   overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void findId() {
        fir = findViewById(R.id.img1);
        sec = findViewById(R.id.img2);
        back = findViewById(R.id.back_arrow);
     //   anim = findViewById(R.id.celebration);


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ChooseActivity.this,StartActivity.class);
        startActivity(intent);
    }

}