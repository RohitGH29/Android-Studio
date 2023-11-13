package com.example.notes;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseData {
    public static ArrayList<DataModel> listOfQ;
    public static DatabaseReference mDatabase;
    public static void main(String[] args) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Second");

        myRef.setValue("hogaya").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               // Toast.makeText(FirebaseData.class, "Done suceesfull", Toast.LENGTH_SHORT).show();
            }
        });
       // firebaseFetch();
    }

    public static void firebaseFetch() {
        listOfQ = new ArrayList<>();


        // Initialize the Firebase Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("subjects");

        // Read data from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle the data when it is available

                    DataModel value = dataSnapshot.getValue(DataModel.class);
                    Log.d("FirebaseData", "Value: " + value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
                Log.e("FirebaseError", "Error: " + databaseError.getMessage());
            }
        });
    }


}

