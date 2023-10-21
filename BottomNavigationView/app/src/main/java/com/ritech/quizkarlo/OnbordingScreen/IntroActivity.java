package com.ritech.quizkarlo.OnbordingScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.ritech.quizkarlo.Login.LoginActivity;
import com.ritech.quizkarlo.MainActivity;
import com.ritech.quizkarlo.R;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new SlideFragmentBuilder()
                        .title("100+ QUESTIONS FROM PAST PAPERS")
                       // .description("(It helps us to take accurate location of your recorded calls)")
                        .buttonsColor(R.color.teal_200)
                        .backgroundColor(R.color.teal_200)
                .image(R.drawable.book)
                        .build()
               );


        addSlide(new SlideFragmentBuilder()
                        .title("GET INSTANT TEST RESULT")
                        .buttonsColor(R.color.teal_200)
                        .backgroundColor(R.color.teal_200)
                .image(R.drawable.boy)
                        .build()
               );


    }


    @Override
    public void onFinish() {
        super.onFinish();

        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}