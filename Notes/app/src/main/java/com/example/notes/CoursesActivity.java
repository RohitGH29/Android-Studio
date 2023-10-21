package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class CoursesActivity extends AppCompatActivity {

    private ListView coursesListView;
    private String[] courseList = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        coursesListView = findViewById(R.id.coursesListView);

        // Create an ArrayAdapter to populate the ListView with course names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        coursesListView.setAdapter(adapter);

        // Set a click listener to handle course selection
        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = courseList[position];
                // Pass selected course information to the CourseDetailActivity
                Intent intent = new Intent(CoursesActivity.this, CourseDetailActivity.class);
                intent.putExtra("courseName", selectedCourse);
                startActivity(intent);
            }
        });
    }
}
