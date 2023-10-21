package com.ritech.uprationcardlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ironsource.mediationsdk.IronSource;

public class MainActivity extends AppCompatActivity {

    CardView list ,complaine , cStatus , more ;
    private FirebaseAnalytics mFirebaseAnalytics;


    final static int SPLASH_TIMER = 8000;

    private ImageView qurekaLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Firebase notification
        FirebaseMessaging.getInstance().subscribeToTopic("a");

        // find all ids
        findViewId();


        // CARD VIEW clicked
        onClick();

        // This code is for Interstitial ad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IronSource.showInterstitial("DefaultInterstitial");
            }
        },SPLASH_TIMER);


    }

    private void onClick() {
        list.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://nfsa.up.gov.in/Food/citizen/Default.aspx";
                String head = "उoप्रo राशन कार्ड सूची देखें";
                intent(link, head);
            }
        });

        complaine.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://cms.up.gov.in/jsk/User/Complain_New_Public.aspx";
                String head = "शिकायत पंजीकरण";
                intent(link, head);
            }
        });

        cStatus.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://cms.up.gov.in/jsk/User/compstatus.aspx";
                String head = "शिकायत की स्थिति देखें";
                intent(link, head);
            }
        });

        more.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String link = "https://play.google.com/store/apps/developer?id=Ritech";
                String head = "More Apps";
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

    private void findViewId() {
        list=findViewById(R.id.list);
        complaine=findViewById(R.id.list2);
        cStatus=findViewById(R.id.list3);
        more=findViewById(R.id.list4);
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
                String link = "https://privacypolicy2910.blogspot.com/2022/03/u.html";
                String head = "Privacy Policy";
                intent(link, head);
                return true;

            case R.id.feedback_op:
                Intent launchEmailAppIntent = new Intent(Intent.ACTION_SENDTO);
                launchEmailAppIntent.setData(Uri.parse("mailto:channelone17@gmail.com")); // only email app handle this
                startActivity(launchEmailAppIntent);
                return true;

            case R.id.disclaimer:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                View view = getLayoutInflater().inflate(R.layout.disclaimer_layout, null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}