package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userEmailTextView;
    private EditText userNameEditText;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users"); // Adjust the path as needed
        userEmailTextView = findViewById(R.id.userEmailTextView);
        userNameEditText = findViewById(R.id.userNameEditText);

//        // Display user email
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            userEmailTextView.setText(user.getEmail());
//        }
        // Get the current user
        userName();


    }

    private void userName() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Create a reference to the user's data in the database
            DatabaseReference userRef = databaseReference.child(userId);

            // Read the display name from the database
            userRef.child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String displayName = dataSnapshot.getValue(String.class);
                        userEmailTextView.setText("Display Name: " + displayName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }
    }

    // Update user profile
    public void updateUserProfile(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String newUserName = userNameEditText.getText().toString();
            updateProfileName(newUserName);

//            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                    .setDisplayName(newUserName)
//                    .build();
//
//            user.updateProfile(profileUpdates)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                // Profile update successful
//                                userNameEditText.setText("");  // Clear the input field
//                                userEmailTextView.setText(user.getEmail()); // Update email display
//                            }
//                        }
//                    });
        }

    }

    private void updateProfileName(String updatedName) {
        DatabaseReference userRef = databaseReference.child(mAuth.getCurrentUser().getUid());

        userRef.child("Name").setValue(updatedName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    userNameEditText.setText("");
                    Toast.makeText(UserProfileActivity.this, "Name updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfileActivity.this, "Name update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // In the onCreate method of UserProfileActivity or another relevant location
       // DatabaseReference userRef = databaseReference.child(mAuth.getCurrentUser().getUid());

        userRef.child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String displayName = dataSnapshot.getValue(String.class);
                    // Update your UI with the new profile name
                    userEmailTextView.setText(displayName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });


    }


    public void onLogoutClick(View view) {
        mAuth.signOut(); // Sign the user out

        // After signing out, you can redirect the user to the login activity or any other relevant activity.
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the activity stack
        startActivity(intent);
        finish(); // Finish the MainActivity
    }

}
