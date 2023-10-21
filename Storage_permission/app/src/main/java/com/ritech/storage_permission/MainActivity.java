package com.ritech.storage_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public int PERMISSION_REQUEST_CODE = 1;
    private Button btn;

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn);

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        // Check if the permission is already granted.
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Already granted", Toast.LENGTH_SHORT).show();
            // Request the permission.
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    PERMISSION_REQUEST_CODE);

                    ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Arrays.toString(PERMISSIONS)},
                    PERMISSION_REQUEST_CODE);
                }

                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
        }});


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // The permission was granted.
                Toast.makeText(this, "requestCode", Toast.LENGTH_SHORT).show();
                Log.d("my", "requestCode  "+String.valueOf(requestCode));
                Log.d("my","Permission  "+ String.valueOf(PERMISSION_REQUEST_CODE));
                Log.d("my", "grantResults  "+String.valueOf(grantResults[0]));
                Log.d("my", "PackageManager.PERMISSION_GRANTED  "+String.valueOf(PackageManager.PERMISSION_GRANTED));
                // You can now access external storage.
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            } else {
                // The permission was denied.
                Toast.makeText(this, "The permission was denied.", Toast.LENGTH_SHORT).show();
               // Toast.makeText(this, requestCode, Toast.LENGTH_SHORT).show();
                Log.d("my", String.valueOf(requestCode));
                Log.d("my", String.valueOf(PERMISSION_REQUEST_CODE));


                // You cannot access external storage.

            }
        }
    }



}