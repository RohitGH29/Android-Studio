package com.example.demoanimalsound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  //  private ArrayList<Animal> animalArrayList;
    private int[] imageResourcesID;
    private int[] audioID;
    private RecyclerView recylerview;

    private Adapter adapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        // array of data
        dataInitialize();

        // calling recycler view and pass array of data in recycler view
        recyclerView();


    }

    private void recyclerView() {
        recylerview = findViewById(R.id.recylerView);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setHasFixedSize(true);
        adapter = new Adapter(this,DbQuery.animalArrayList);
        recylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        //animalArrayList = new ArrayList<>();

        audioID = new int[]{
                R.raw.ostrich,
                R.raw.pig,
                R.raw.pigeon,
                R.raw.rabbit,
                R.raw.rat,
                R.raw.sheep,
                R.raw.tiger,
                R.raw.zebra,

        };

        imageResourcesID = new int[]{
                R.drawable.cat,
                R.drawable.cow,
                R.drawable.crow,
                R.drawable.deer,
                R.drawable.cat,
                R.drawable.duck,
                R.drawable.eagle,
                R.drawable.elephant,

        };

        for(int i=0;i<imageResourcesID.length;i++){

            Animal news= new Animal(imageResourcesID[i],audioID[i]);
            DbQuery.animalArrayList.add(news);
        }

    }

}