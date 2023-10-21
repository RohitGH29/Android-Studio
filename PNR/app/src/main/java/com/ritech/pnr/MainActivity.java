package com.ritech.pnr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText pnr_no;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pnr_no=findViewById(R.id.pnr_number);
        button=findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnr =pnr_no.getText().toString();
                Log.d("myapp","format: " + pnr);
                Log.d("itsapp", "Date:" + pnr) ;
                Intent intent = new Intent(MainActivity.this,PnrShow.class);
                intent.putExtra("pnr_number",pnr);
                startActivity(intent);


            }
        });
    }


}