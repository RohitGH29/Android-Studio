package com.ritech.pnr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PnrShow extends AppCompatActivity {

    TextView pnr_number,train_name,journey_date,chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_show);


        pnr_number=findViewById(R.id.pnr_number_show);
        train_name=findViewById(R.id.train_name);
        journey_date=findViewById(R.id.journey_date);
        chart=findViewById(R.id.chart);

        // For get the data from MainActivity.
        Intent intent= getIntent();
        String pnr= intent.getStringExtra("pnr_number");

        fetchData(pnr);
    }

    private void fetchData(String pnr) {
        String url ="https://pnr-status-indian-railway.p.rapidapi.com/rail/"+pnr;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "pnr-status-indian-railway.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "12ed1697f9mshb6e6505689ea560p13f681jsnf26a20cd4753")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(PnrShow.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful())
                {
                    String resp = response.body().string();
                    PnrShow.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                String trainNo = jsonObject.getString("train_number");
                                String trainName = jsonObject.getString("train_name");
                                String passengerDetails = jsonObject.getString("passenger");
                                String val4 = jsonObject.getString("reservation_upto");
                                String val5 = jsonObject.optString("chart_status");
                                String val6 = jsonObject.getString("boarding_station");
                                String val7 = jsonObject.getString("passenger");
                                String val8 = jsonObject.optString("arrival_data");
                             //   String val9 = jsonObject.getString("departure_data");
                                String val10 = jsonObject.optJSONObject("departure_data").optString("departure_time");
                                String val11 = jsonObject.optJSONObject("departure_data").optString("departure_date");
                                Log.d("myapp","Direct :" +val11);
                                String currentDate = DateFormat.getDateInstance().format(val11);
                                Log.d("myapp","currentDate: " +currentDate);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    Date d = simpleDateFormat.parse(val11);
                                    Log.d("myapp","format: " + d);
                                    chart.setText("Departure Date \t-\t"+ d);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                                pnr_number.setText("Train no \t-\t" + trainNo);
                                train_name.setText("Departure Date"+"\t-\t"+ trainName);
                                journey_date.setText("Journey Time \t-\t"+val10);
                              //  chart.setText("Departure Date \t-\t"+ );
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