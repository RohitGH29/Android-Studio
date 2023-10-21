package com.ritech.shapeslearningforkids;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
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
        //  Bundle extras = getIntent().getExtras();
        //getStr = extras.getString("a");


        // calling recycler view and pass array of data in recycler view
        recyclerView();


    }


    private void backArrow() {
        backBtn = findViewById(R.id.back_arrow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void recyclerView() {

        RecyclerView recylerview = findViewById(R.id.recylerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setHasFixedSize(true);
        Adapter adapter;
        adapter = new Adapter(this, Db.shapesArrayList);
        //Toast.makeText(this, getStr, Toast.LENGTH_SHORT).show();
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
        adView.setAdUnitId("ca-app-pub-5924275746208514/2716087932");
        //start requesting banner ads
        loadBanner();
        if (StartActivity.check==0) {
            loadInterstitial();
            StartActivity.check=1;// call interstitial ad function
        }
    }

    // interstitial ad loading
    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        // ca-app-pub-3940256099942544/1033173712 test AD unit id
        InterstitialAd.load(this, "ca-app-pub-5924275746208514/9476937404", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.d("myTag", "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad dismissed fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e(TAG, "Ad failed to show fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("myTag", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        // calling interstitial ad
        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                //call interstitial ad
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        }, 10000);
    }

   /* // now ad loaded call it interstitial ad
    private void callInterstitialAd() {
        // calling interstitial ad
        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call interstitial ad
                mInterstitialAd.show(MainActivity.this);
            }
        }, 10000);
    }*/

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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}


