package com.example.notes;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    public static ArrayList<String> listOfQ;
    public static DatabaseReference mDatabase;
    public ArrayAdapter<String> adapter;
    private ListView coursesListView;
    private String[] courseList = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);


        fireBase();
        coursesListView = findViewById(R.id.coursesListView);

        // Create an ArrayAdapter to populate the ListView with course names
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfQ);

        coursesListView.setAdapter(adapter);

        // Set a click listener to handle course selection
//        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedCourse = courseList[position];
//                // Pass selected course information to the CourseDetailActivity
//                Intent intent = new Intent(CoursesActivity.this, CourseDetailActivity.class);
//                intent.putExtra("courseName", selectedCourse);
//                startActivity(intent);
//            }
//        });
    }

    private void fireBase() {
        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message2");
//
//        myRef.setValue("Hello, World!").addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                  Toast.makeText(CoursesActivity.this, "Done suceesfull", Toast.LENGTH_SHORT).show();
//            }
//        });

        listOfQ = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference("subjects");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                  String sub  =snapshot.getValue(String.class);
                  listOfQ.add(sub);
                  adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                    String modalClass = snapshot.getValue(String.class);
//                    Log.d("mytag","value: "+ modalClass);
//                    listOfQ.add(modalClass);
//                    Toast.makeText(CoursesActivity.this, "Sucess"+listOfQ, Toast.LENGTH_SHORT).show();
//                    Log.d("mytag","valuess: "+ listOfQ);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(CoursesActivity.this, "Please check internet Connection", Toast.LENGTH_SHORT).show();
//
//            }
//        });



    }

}
