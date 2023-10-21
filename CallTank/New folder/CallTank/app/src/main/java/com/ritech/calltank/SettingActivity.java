package com.ritech.calltank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    private CardView callB, privacyB, shareB, accountB, logoutB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.aa_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findId();

        onClick();

    }

    private void findId() {
        callB = findViewById(R.id.contactUsB);
        privacyB=findViewById(R.id.privacyPolicyB);
        shareB=findViewById(R.id.shareB);
        accountB=findViewById(R.id.accountB);
        logoutB=findViewById(R.id.logoutB);
    }

    private void onClick()
    {
        callB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = "7737682767";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + no));//change the number
                startActivity(callIntent);
            }
        });

        privacyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shareB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Share this App ";
                //String sharesub = "";
               // shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Via"));

            }
        });

        accountB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}