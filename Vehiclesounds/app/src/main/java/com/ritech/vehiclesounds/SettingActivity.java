package com.ritech.vehiclesounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    private TextView privacyB, shareB, rateB;
    private ImageView backB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        findId();

        onClick();

    }

    private void onClick() {

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });


        privacyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/upsc-books-list.appspot.com/o/vehicle_sound_privacy_policy.txt?alt=media&token=549b1652-3294-4d7d-99f3-6caadf30c474");
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });


        rateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);

            }
        });

        shareB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = " Download Animals Sound app :  https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Via"));
            }
        });

    }

    private void findId() {
        privacyB=findViewById(R.id.privacy_policy);
        shareB=findViewById(R.id.share);
        rateB=findViewById(R.id.rate_us);
        backB=findViewById(R.id.back_btn);
    }
}