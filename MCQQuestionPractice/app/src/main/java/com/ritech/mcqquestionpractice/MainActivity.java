package com.ritech.mcqquestionpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Db.questions();
        recyclerView();

    }


    private void recyclerView() {
        RecyclerView recylerview = findViewById(R.id.recylerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setHasFixedSize(true);
        myAdapter adapter = new myAdapter(this,Db.questionArrayList);
        //Log.e("Mytag", String.valueOf(Db.questionArrayList));
        recylerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}