package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrContact= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recylerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"A","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"B","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"C","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"D","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"E","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"F","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"G","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"H","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"I","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,"J","8282828676"));

        // now adapter calling
        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this,arrContact);
        recyclerView.setAdapter(adapter);



    }
}