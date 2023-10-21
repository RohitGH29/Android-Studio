package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userEmailTextView;
    private EditText userNameEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        userEmailTextView = findViewById(R.id.userEmailTextView);
        userNameEditText = findViewById(R.id.userNameEditText);

        // Display user email
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userEmailTextView.setText(user.getEmail());
        }
    }

    // Update user profile
    public void updateUserProfile(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String newUserName = userNameEditText.getText().toString();

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(newUserName)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Profile update successful
                                userNameEditText.setText("");  // Clear the input field
                                userEmailTextView.setText(user.getEmail()); // Update email display
                            }
                        }
                    });
        }
    }
}
