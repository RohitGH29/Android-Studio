package com.ritech.storage_permission;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

// Inflate the dialog box layout
        View dialogLayout = inflater.inflate(R.layout.dialog, null);

// Create a new dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// Set the dialog view
        builder.setView(dialogLayout);

// Set the dialog title
        builder.setTitle("Requesting permission");

// Set the dialog message
        builder.setMessage("We need your permission to access your location.");

// Set the dialog buttons
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Allow the permission
            }
        });

        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Deny the permission
            }
        });

// Show the dialog
        builder.show();



    }
}