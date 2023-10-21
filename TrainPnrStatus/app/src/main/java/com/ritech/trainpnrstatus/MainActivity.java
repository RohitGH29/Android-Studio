package com.ritech.trainpnrstatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private LinearLayout pnr,schedule,running,between;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));

        pnr=findViewById(R.id.pnrLayout);
        schedule=findViewById(R.id.scheduleLayout);
        running=findViewById(R.id.runningLayout);
        between=findViewById(R.id.betweenLayout);

        onclick();


    }

    private void onclick() {
        // Pnr Btn Click
        pnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PnrStatus.class);
                startActivity(intent);
            }
        });

        //Schedule Btn Click
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TrainSchedule.class);
                startActivity(intent);
            }
        });

        //Running Btn
        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RunningStatus.class);
                startActivity(intent);
            }
        });

        //Stations Between
        between.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BetweenStations.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}