package com.ritech.songs;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int PERMIT = 1;

    ContentResolver contentResolver;
    Cursor cursor;
    Context context;
    String[] mystringarray = new String[]{};
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Uri uri;


    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        listView = findViewById(R.id.listview);
        context = getApplicationContext();
        arrayList = new ArrayList<>(Arrays.asList(mystringarray));
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList);

        getpermit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getmp3();

                listView.setAdapter(arrayAdapter);
            }
        });

    }

    private void getpermit() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    AlertDialog.Builder alertb = new AlertDialog.Builder(MainActivity.this);

                    alertb.setTitle("Give ");
                    alertb.setMessage("Read");
                    alertb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMIT);
                        }
                    });

                    alertb.setNegativeButton("Cancel", null);
                    AlertDialog alertDialog = alertb.create();
                    alertDialog.show();

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMIT);
                }

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMIT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    ;
            }
        }

    }

    private void getmp3() {

        contentResolver = context.getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor == null) {
            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
        } else if (!cursor.moveToFirst()) {
            Toast.makeText(context, "No Music found", Toast.LENGTH_SHORT).show();
        } else {
            int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);


            do {
                String st = cursor.getString(   title);

                arrayList.add(st);

            } while (cursor.moveToNext());
        }


    }


}