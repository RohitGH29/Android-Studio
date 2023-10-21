package com.example.notes;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // Get data passed from the previous activity (CoursesActivity)
        Intent intent = getIntent();
        if (intent != null) {
            String courseName = intent.getStringExtra("courseName");
            String courseDescription = getCourseDescription(courseName); // Replace with your data source
            displayCourseDetails(courseName, courseDescription);
        }
    }

    // Replace this method with your data retrieval logic (e.g., fetching course details from a database)
    private String getCourseDescription(String courseName) {
        // Simulated course description
        if (courseName.equals("Course 1")) {
            return "This is the description for Course 1.";
        } else if (courseName.equals("Course 2")) {
            return "This is the description for Course 2.";
        } else {
            return "Description not available.";
        }
    }

    private void displayCourseDetails(String courseName, String courseDescription) {
        TextView courseTitle = findViewById(R.id.courseTitle);
        TextView courseDesc = findViewById(R.id.courseDescription);

        courseTitle.setText(courseName);
        courseDesc.setText(courseDescription);
    }
}
