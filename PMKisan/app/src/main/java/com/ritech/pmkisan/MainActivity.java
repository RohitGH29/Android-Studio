package com.ritech.pmkisan;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ironsource.mediationsdk.IronSource;


public class MainActivity extends AppCompatActivity {

    CardView reg, bstatus, blist, home, aadhar, moreapps;
    private FirebaseAnalytics mFirebaseAnalytics;

    private ImageView qurekaLite;

    final static int SPLASH_TIMER = 8000;

    public static int UPDATE_CODE = 22;
    AppUpdateManager appUpdateManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase notification
        FirebaseMessaging.getInstance().subscribeToTopic("a");


        // This code is for Interstitial ad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IronSource.showInterstitial("DefaultInterstitial");
            }
        }, SPLASH_TIMER);


        // find all ids
        findViewId();


        // CARD VIEW clicked
        onClick();

        // for app update check inside the app
        // appUpdate();
        inAppUp();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }


    private void onClick() {

        reg.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://pmkisan.gov.in/RegistrationFormnew.aspx";
                String head = "नया पंजीकरण";
                intent(link, head);
            }
        });

        bstatus.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://pmkisan.gov.in/BeneficiaryStatus.aspx";
                String head = "लाभार्थी की स्थिति";
                intent(link, head);
            }
        });


        moreapps.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://play.google.com/store/apps/developer?id=Ritech";
                String head = "More Apps";
                intent(link, head);
            }
        });

        blist.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://pmkisan.gov.in/Rpt_BeneficiaryStatus_pub.aspx";
                String head = "लाभार्थी सूच";
                intent(link, head);
            }
        });

        home.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://pmkisan.gov.in/";
                String head = "Home";
                intent(link, head);
            }
        });

        aadhar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://pmkisan.gov.in/UpdateAadharNoByFarmer.aspx";
                String head = "Edit Aadhaar Records";
                intent(link, head);
            }

        });


        qurekaLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri url = Uri.parse("https://647.go.qureka.co/");
                Intent i = new Intent(Intent.ACTION_VIEW, url);
                startActivity(i);

            }
        });

    }


    private void intent(String link, String head) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        Bundle extras = new Bundle();
        extras.putString("url", link);
        extras.putString("heading", head);
        intent.putExtras(extras);
        startActivity(intent);
    }


    // Finding all Id
    private void findViewId() {
        reg = findViewById(R.id.registration);
        bstatus = findViewById(R.id.bstatus);
        home = findViewById(R.id.home);
        moreapps = findViewById(R.id.more_apps);
        blist = findViewById(R.id.blist);
        aadhar = findViewById(R.id.aadhar);
        //  about = findViewById(R.id.about_btn);
        qurekaLite = findViewById(R.id.ad_container);
    }


    // For action bar show
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    // on action bar clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rate_op:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                return true;

            case R.id.share_op:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "To check and Registration For PM Kisan Samman Nidhi Yojna Download this app : https://play.google.com/store/apps/details?id=com.ritech.pmkisan ";
                String sharesub = "PM Kisan Samman Nidhi";

                shareIntent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
                return true;

            case R.id.privacy_op:
                String link = "https://privacypolicy2910.blogspot.com/2021/11/pm-kisan-samman-nidhi-yojana.html";
                String head = "Privacy Policy";
                intent(link, head);
                return true;

            case R.id.feedback_op:
                Intent launchEmailAppIntent = new Intent(Intent.ACTION_SENDTO);
                launchEmailAppIntent.setData(Uri.parse("mailto:channelone17@gmail.com")); // only email app handle this
                startActivity(launchEmailAppIntent);
                return true;

            case R.id.contact_op:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                View view = getLayoutInflater().inflate(R.layout.contact_us_layout, null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            case R.id.updateApp:
                Uri uriUpdate = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                Intent update = new Intent(Intent.ACTION_VIEW, uriUpdate);
                startActivity(update);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void inAppUp() {
        appUpdateManager = AppUpdateManagerFactory.create(this);

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> task = appUpdateManager.getAppUpdateInfo();
        task.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {

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

            }
        });
        appUpdateManager.registerListener(listener);
    }

    InstallStateUpdatedListener listener = installState -> {

        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            popUp();
        }
    };

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_CODE) {

            if (resultCode != RESULT_OK) {

            }
        }
    }


}
