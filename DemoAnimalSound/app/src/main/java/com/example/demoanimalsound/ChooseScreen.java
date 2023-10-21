package com.example.demoanimalsound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ChooseScreen extends AppCompatActivity {

   // private ArrayList<Choose> chooseArrayList;
    private int[] imgId;
    private RecyclerView recylerview;

    private ChooseAdapter chooseAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        getSupportActionBar().hide();

        // array of data
        dataInitialize();
        recyler();


    }

    private void dataInitialize() {
       // chooseArrayList = new ArrayList<>();

        imgId = new int[]{
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.duck,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,
                R.drawable.cat,

        };
        for(int i=0;i<imgId.length;i++){

            Choose choose= new Choose(imgId[i]);
            DbQuery.chooseArrayList.add(choose);
        }
    }

    private void recyler() {
        recylerview = findViewById(R.id.recylerViewChoose);
        gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);
        recylerview.setLayoutManager(gridLayoutManager);
        recylerview.setHasFixedSize(true);
        chooseAdapter = new ChooseAdapter(this, DbQuery.chooseArrayList);
        recylerview.setAdapter(chooseAdapter);
        chooseAdapter.notifyDataSetChanged();

    }


}