package com.ritech.pmkisan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SchemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        getSupportActionBar().setTitle("योजना के बारे में");

    }
}