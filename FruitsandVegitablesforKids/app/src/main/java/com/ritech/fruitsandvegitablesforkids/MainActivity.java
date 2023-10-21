package com.ritech.fruitsandvegitablesforkids;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;


import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    public ImageView backBtn;

    // app update check
    public static int UPDATE_CODE = 22;
    AppUpdateManager appUpdateManager;
    String getStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //call admob ads
         admob();

        backArrow();


        // Firebase notification
        //  FirebaseMessaging.getInstance().subscribeToTopic("animal_sounds");


        //check app update
        AppUpdate();

        // array of data
        //dataInitialize();
        Bundle extras = getIntent().getExtras();
        getStr = extras.getString("a");


        // calling recycler view and pass array of data in recycler view
        recyclerView();


    }


    private void backArrow() {
        backBtn=findViewById(R.id.back_arrow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(intent);
                  overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    private void recyclerView() {

        RecyclerView recylerview = findViewById(R.id.recylerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setHasFixedSize(true);
        Adapter adapter;
        if (getStr.equals("an")) {
            adapter = new Adapter(this, Db.animalArrayList);
            //Toast.makeText(this, getStr, Toast.LENGTH_SHORT).show();
        } else if (getStr.equals("le")) {
            adapter = new Adapter(this, Db.alphabetsArrayList);
            //Toast.makeText(this, getStr, Toast.LENGTH_SHORT).show();
        } else if(getStr.equals("no")) {
            adapter = new Adapter(this, Db.numberArrayList);
            // Toast.makeText(this, getStr, Toast.LENGTH_SHORT).show();
        }else if(getStr.equals("tr")){
            adapter = new Adapter(this, Db.vehicleArrayList);
        }else if(getStr.equals("fr")){
            adapter = new Adapter(this,Db.fruitArrayList);
        }else {
            adapter =new Adapter(this,Db.vegetableArrayList);
        }

        recylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void admob() {
        //Call the function to initialize AdMob SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        //get the reference to your FrameLayout
        // Admob
        FrameLayout adContainerView = findViewById(R.id.adView_container);

        //Create an AdView and put it into your FrameLayout
        adView = new AdView(this);
        adContainerView.addView(adView);
        //ca-app-pub-3940256099942544~3347511713 test app id
        //ca-app-pub-3940256099942544/6300978111 test add unit id
        adView.setAdUnitId("ca-app-pub-5924275746208514/1457136723");
        //start requesting banner ads
        loadBanner();

        if (ChooseActivity.check==0) {
            loadInterstitial();
            ChooseActivity.check=1;// call interstitial ad function
        }
    }

    private void loadBanner() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        AdSize adSize = getAdSize();
        // Set the adaptive ad size to the ad view.
        adView.setAdSize(adSize);

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        //Determine the screen width to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        //you can also pass your selected width here in dp
        int adWidth = (int) (widthPixels / density);

        //return the optimal size depends on your orientation (landscape or portrait)
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }


    //interstitial ad function
    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        // c test AD unit id
        InterstitialAd.load(this,"ca-app-pub-5924275746208514/8320396692", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.d("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("TAG", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        // calling interstitial ad
        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call interstitial ad
                mInterstitialAd.show(MainActivity.this);
            }
        }, 10000);
    }


    // app update
    private void AppUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this);

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> task = appUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener((OnSuccessListener<? super AppUpdateInfo>) appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE
                            , MainActivity.this, UPDATE_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                    Log.d("itsapp", "onSucess: " + e.toString());
                }
            }
        });
        appUpdateManager.registerListener(listener);
    }


    // app update
    InstallStateUpdatedListener listener = installState -> {

        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            popUp();
        }
    };

    // app update
    private void popUp() {

        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content), "App Update Almost Done",
                Snackbar.LENGTH_INDEFINITE
        );

        snackbar.setAction("Reload", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(Color.parseColor("#FF0000"));
        snackbar.show();
    }

    // app update
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_CODE) {

            if (resultCode != RESULT_OK) {

            }
        }
    }


    // slide animation
    @Override
    public void finish() {
        super.finish();
         overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }


}