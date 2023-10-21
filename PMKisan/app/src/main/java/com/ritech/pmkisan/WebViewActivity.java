package com.ritech.pmkisan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.BannerListener;


public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // iron source ad code
        ironsource();

        progressBar = findViewById(R.id.progressBar);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String url = extras.getString("url");
        String head = extras.getString("heading");

        getSupportActionBar().setTitle(head);


        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(url);


    }

    private void ironsource() {

        IronSource.init(this, "12f0911fd", IronSource.AD_UNIT.BANNER);
        final FrameLayout bannerContainer = findViewById(R.id.bannerContainer);
        IronSourceBannerLayout banner = IronSource.createBanner(this, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bannerContainer.addView(banner, 0, layoutParams);
        banner.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
// Called after a banner ad has been successfully loaded
                banner.setVisibility(View.VISIBLE);
                Log.d("itsapp", "Loaded sucess");
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError error) {
                Log.d("itsapp", "Error :" + error);
// Called after a banner has attempted to load an ad but failed.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bannerContainer.removeAllViews();
                    }
                });
            }

            @Override
            public void onBannerAdClicked() {
// Called after a banner has been clicked.
            }

            @Override
            public void onBannerAdScreenPresented() {
// Called when a banner is about to present a full screen content.
            }

            @Override
            public void onBannerAdScreenDismissed() {
// Called after a full screen content has been dismissed
            }

            @Override
            public void onBannerAdLeftApplication() {
// Called when a user would be taken out of the application context.
            }
        });
        IronSource.loadBanner(banner);
// YOUR OTHER CODE //
// YOUR OTHER CODE //
// YOUR OTHER CODE //

    }

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}