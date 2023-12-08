package com.example.jsonparsingpostapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        AndroidNetworking.initialize(this);
        AndroidNetworking.post("https://wscubetech.org/android-course/get.php")
                .addBodyParameter("course_id")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("myapp",response.toString());

                        try {
                            JSONObject objData= response.getJSONObject("data");
                            String name= objData.getString("name");
                            TextView textView = findViewById(R.id.textView);
                            textView.setText(name);
                            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            Log.d("Namemy",name);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);
                          //  Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {


                    }
                });

    }
}