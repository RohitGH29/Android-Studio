package com.ritech.calltank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new SlideFragmentBuilder()
                        .title("Please allow auto call recording on your device to get better experience")
                        .buttonsColor(R.color.teal_200)
                        .backgroundColor(R.color.set)
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We provide solutions to make you love your work");
                    }
                }, "I Got it, Continue"));


        addSlide(new SlideFragmentBuilder()
                        .title("Please make a dummy call")
                        .description("(It helps us to take accurate location of your recorded calls)")
                        .buttonsColor(R.color.teal_200)
                        .backgroundColor(R.color.set)
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"));//change the number
                        startActivity(callIntent);
                    }
                }, "Make a dummy call"));


        addSlide(new SlideFragmentBuilder()
                        .title("Is this is your recorded dummy call?")
                        .buttonsColor(R.color.teal_200)
                        .backgroundColor(R.color.set)
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, "No"));



    }

    @Override
    public void onFinish() {
        super.onFinish();
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}