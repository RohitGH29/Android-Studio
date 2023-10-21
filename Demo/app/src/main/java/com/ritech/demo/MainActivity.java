package com.ritech.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Modal> animalArrayList = new ArrayList<>();
    public static int[] imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageId= new int[]{
              R.drawable.airship,
              R.drawable.bike,
              R.drawable.bus,
              R.drawable.car,
              R.drawable.excavator,
              R.drawable.cycle,
              R.drawable.firetruck,
        };

        for(int i=0;i<imageId.length;i++){

            Modal news= new Modal(imageId[i]);
            animalArrayList.add(news);


        }

        recyclerView();

    }

    public void recyclerView() {
        RecyclerView recylerview = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setHasFixedSize(true);
        Adapter adapter;
        adapter = new Adapter(this, animalArrayList);
        recylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        final int i=adapter.getItemCount();
        final int sou = 1;
        final int tgt=i;
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mytag", String.valueOf(i));
                Log.d("Mytag", String.valueOf(sou));
                Log.d("Mytag", String.valueOf(tgt));
               // if(a[i]!=animalArrayList.size()){
              adapter.notifyItemMoved(sou, sou +1 );
              //sou[0] = sou[0] +1;
               // Toast.makeText(MainActivity.this, i, Toast.LENGTH_SHORT).show();

            //  a[i] = a[i] +1;
               // i++;


            }
        });

    }


}