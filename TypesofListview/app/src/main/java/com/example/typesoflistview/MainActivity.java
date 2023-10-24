package com.example.typesoflistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrNames = new ArrayList<>();  // this array is use for store data related to list view
    ArrayList<String> arrIds = new ArrayList<>();  // this array is use for store data related to spinner
    ArrayList<String> arrAuto = new ArrayList<>();// this array is use for store data related to auto complete list view

    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ids();

        ListViews();

        SpinnerView();

        AutoComplete();

    }

    private void AutoComplete() {   //Auto CompletetextView
        arrAuto.add("C");
        arrAuto.add("C++");
        arrAuto.add("Java");
        arrAuto.add("PHP");
        arrAuto.add("Html");
        arrAuto.add("Css");
        arrAuto.add("JavaScript");
        arrAuto.add("C#");

        ArrayAdapter<String> autoAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrAuto);
        autoCompleteTextView.setAdapter(autoAdapter);
        autoCompleteTextView.setThreshold(1);  // this is use to start autocomplete from which letter e.g:-(1) means when user input first alphabets autocomplete start
    }

    private void SpinnerView() {
        arrIds.add("Adhar card");
        arrIds.add("Pan card");
        arrIds.add("Voter card");
        arrIds.add("Pension card");
        arrIds.add("BPL card");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrIds);
        spinner.setAdapter(spinnerAdapter);

    }

    private void ListViews() {
        arrNames.add("First");
        arrNames.add("Second");
        arrNames.add("Third");
        arrNames.add("Fourth");
        arrNames.add("Five");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");
        arrNames.add("First");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(MainActivity.this, arrNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void Ids() {
        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        autoCompleteTextView = findViewById(R.id.auto);
    }
}