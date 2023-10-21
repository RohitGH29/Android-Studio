package com.ritech.temperatureconverter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button cBtn, fBtn;
    TextView ans;
    EditText input;
    Double x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle("Temperature Converter");

        findId();
        onClick();
    }

    public void onClick() {

        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter the Temperature", Toast.LENGTH_SHORT).show();
                } else {
                    x = Double.parseDouble(String.valueOf(input.getText()));
                    x = (x * 9) / 5 + 32;
                    ans.setText(String.valueOf(x) + " " + "F");
                }

            }
        });

        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter the Temperature", Toast.LENGTH_SHORT).show();
                } else {
                    x = Double.parseDouble(String.valueOf(input.getText()));
                    x = (x - 32) * 5 / 9;
                    ans.setText(String.valueOf(x) + " " + "C");
                }
            }
        });

    }

    private void findId() {
        input = findViewById(R.id.temp_Edit);
        ans = findViewById(R.id.ansText);
        cBtn = findViewById(R.id.buttonC);
        fBtn = findViewById(R.id.buttonF);
    }


}