package com.ritech.lcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private EditText in1,in2;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in1=findViewById(R.id.yourname);
        in2=findViewById(R.id.partnername);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringy = in1.getText().toString();
                String stringp = in2.getText().toString();
                fetchData(stringy,stringp);
            }
        });
    }

    private void fetchData(String stringy, String stringp) {

        String url = "https://love-calculator.p.rapidapi.com/getPercentage?sname="+stringp+"&fname="+stringy;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "love-calculator.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "12ed1697f9mshb6e6505689ea560p13f681jsnf26a20cd4753")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful())
                {
                    String resp = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                String val1 = jsonObject.getString("percentage");
                                String val2 = jsonObject.getString("result");
                                String val3 = jsonObject.getString("fname");
                                String val4 = jsonObject.getString("sname");
                                textView.setText(val3+"\n"+val4+"\n"+val1+"%\n"+val2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });





    }
}