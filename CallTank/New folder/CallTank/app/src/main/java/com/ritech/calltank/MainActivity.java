package com.ritech.calltank;

import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button contactCreateB, dummyCallB , callTank, settingB;
    int requestCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.aa_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Call Tank");

        findID();

        checkPermission();

        onClick();

    }

    private void onClick() {

        dummyCallB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" ));//change the number
                startActivity(callIntent);
            }
        });

        settingB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        callTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AudioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findID() {

        contactCreateB = findViewById(R.id.createContactB);
        dummyCallB = findViewById(R.id.dummyCallB);
        callTank = findViewById(R.id.call_tankB);
        settingB=findViewById(R.id.settingB);
    }


    private void checkPermission() {

        if(SDK_INT>23)
        {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                //maincode
                Toast.makeText(getApplicationContext(), "Already Grandet", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new  String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestCode);
                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Already", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            boolean read = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (read)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                    Toast.makeText(this, "Again ask", Toast.LENGTH_SHORT).show();

            }

        }

    }
}