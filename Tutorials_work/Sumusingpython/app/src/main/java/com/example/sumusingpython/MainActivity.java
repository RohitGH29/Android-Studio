package com.example.sumusingpython;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// "context" must be an Activity, Service or Application object from your app.
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
//
//        Python py = Python.getInstance();
//        Python
    }

    public void calculateSum(View view) {
        EditText num1EditText = findViewById(R.id.num1EditText);
        EditText num2EditText = findViewById(R.id.num2EditText);
        TextView resultTextView = findViewById(R.id.resultTextView);

        double num1 = Double.parseDouble(num1EditText.getText().toString());
        double num2 = Double.parseDouble(num2EditText.getText().toString());

        // Execute Python script to calculate the sum
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("sum.py");
        
            // ProcessBuilder processBuilder = new ProcessBuilder
            //   ("C:/Users/pc/AppData/Local/Programs/Python/Python312/python.exe", "sum.py");

            Process process = processBuilder.start();

            // Communicate with the Python process using input and output streams
            // Pass num1 and num2 as input to the Python script
            OutputStream outputStream = process.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(num1 + "\n");
            writer.write(num2 + "\n");
            writer.flush();
            writer.close();

            // Read the result from the Python script's output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            resultTextView.setText("Result: " + result);
            Toast.makeText(this, "Done" + result, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}