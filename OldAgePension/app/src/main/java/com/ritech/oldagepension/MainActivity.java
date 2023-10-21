package com.ritech.oldagepension;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    CardView old,div,widow,pri;
    private FirebaseAnalytics mFirebaseAnalytics;

    private final String APP_ID = "app6e65c29deccb4125b5";
    private final String BANNER_ZONE_ID = "vzfca4a7c407e9400fb2";

    private AdColonyAdOptions adOptions;
    private AdColonyAdView adView;
    private RelativeLayout adContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ad colony
        AdColony.configure(MainActivity.this,APP_ID, BANNER_ZONE_ID);
        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView adColonyAdView) {
                adContainer = findViewById(R.id.ad_container);
                adContainer.addView(adColonyAdView);
                adView = adColonyAdView;
            }
        };

        AdColony.requestAdView(BANNER_ZONE_ID ,listener, AdColonyAdSize.BANNER , adOptions);




        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // For test pupose
       // MediationTestSuite.launch(MainActivity.this);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));

       old = findViewById(R.id.old_age);
       div = findViewById(R.id.div);
       widow = findViewById(R.id.widow);
       pri = findViewById(R.id.privacy_policy);

       onClick();

    }


    private void onClick() {
        // Click on 1 Btn
        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://sspy-up.gov.in/HindiPages/oldage_h.aspx";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });

        //  Click on 2 Btn
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://sspy-up.gov.in/HindiPages/handicap_h.aspx";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });

        // Clcik on 3 Btn
        widow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://sspy-up.gov.in/HindiPages/widow_h.aspx";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });

        // Clcik 4 btn
        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://privacypolicy2910.blogspot.com/2021/12/privacy-policy-ritech-built-up-pension.html";
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });
    }


}