package com.ritech.intent_bundle_lecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String name = intent.getStringExtra("StudentName");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(name);
        getSupportActionBar().setTitle(title);

    }
}