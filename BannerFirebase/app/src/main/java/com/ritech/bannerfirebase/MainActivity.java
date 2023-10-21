package com.ritech.bannerfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    RelativeLayout banner;
    String data , adUnitId;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        banner=findViewById(R.id.banner_container);
        fireAds("https://bannerfirebase-6071c-default-rtdb.firebaseio.com/banner");

        appId("https://bannerfirebase-6071c-default-rtdb.firebaseio.com/appId");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



    }

    public void fireAds(String adsUrl) {
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase(adsUrl);

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = dataSnapshot.getValue(String.class);
                AdView mAdView = new AdView(MainActivity.this);
                mAdView.setAdUnitId(data);
                banner.addView(mAdView);
                mAdView.setAdSize(AdSize.SMART_BANNER);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

}

public void appId(String url){

        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase(url);

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adUnitId = dataSnapshot.getValue(String.class);

                try {
                    ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                    Bundle bundle = ai.metaData;
                    String myApiKey = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");

                    ai.metaData.putString("com.google.android.gms.ads.APPLICATION_ID", adUnitId);//you can replace your key APPLICATION_ID here
                    String ApiKey = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");

                } catch (PackageManager.NameNotFoundException e) {

                } catch (NullPointerException e) {

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


}

}