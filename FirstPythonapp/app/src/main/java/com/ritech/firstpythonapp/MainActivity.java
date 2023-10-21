package com.ritech.firstpythonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(MainActivity.this));
        }

        //this will start python


        //now create python instance
        Python py = Python.getInstance();
        //now create python object
        PyObject pyobj = py.getModule("main"); // give python script name

        // now call this function

        PyObject obj = pyobj.callAttr("main");

        // now set return text to textview

        textView.setText(obj.toString());


    }
}