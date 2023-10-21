package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    // Example method to open the "Courses" section
    public void openCourses(View view) {
        startActivity(new Intent(this, CoursesActivity.class));
    }

    // Example method to open the "Profile" section
    public void openProfile(View view) {
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    // Add more methods to handle other dashboard features as needed
}
