package com.example.firebaserecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebaserecyclerview.Model.HomeModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public ListView listView;
    public ArrayList<String> arrSubjects = new ArrayList<String>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference subjectsRef = db.collection("Subjects");

    private ArrayAdapter<HomeModel> adapter;

    // private DocumentReference docRef = db.document(subjectsRef.document().getId());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);


//        loadData();
//        adapterWork();
    }

    private void adapterWork() {
        adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, arrSubjects);
        listView.setAdapter(adapter);

    }

    private void loadData() {
        // this is used for testing purpose
//        arrSubjects.add("ds");
//        arrSubjects.add("asd");

//        subjectsRef
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            HomeModel homeModel = documentSnapshot.toObject(HomeModel.class);
//                            String subjectName = homeModel.getSubjectName();
//                            arrSubjects.add(subjectName);
//
//                            Toast.makeText(HomeActivity.this, "HO gaya", Toast.LENGTH_SHORT).show();
//                        }
//                        adapterWork();  // here call the adapter
//                    }
//                });

        subjectsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                arrSubjects.clear();
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    HomeModel homeModel = documentSnapshot.toObject(HomeModel.class);
                    String subjectName = homeModel.getSubjectName();
                    arrSubjects.add(subjectName);

                    Toast.makeText(HomeActivity.this, "HO gaya", Toast.LENGTH_SHORT).show();
                }
                adapterWork();  // here call the adapter
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        loadData();
//        subjectsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
//
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                arrSubjects.clear();
//                for (QueryDocumentSnapshot documentSnapshot : value) {
//                    HomeModel homeModel = documentSnapshot.toObject(HomeModel.class);
//                    String subjectName = homeModel.getSubjectName();
//                    arrSubjects.add(subjectName);
//
//                    Toast.makeText(HomeActivity.this, "HO gaya", Toast.LENGTH_SHORT).show();
//                }
//                adapterWork();  // here call the adapter
//            }
//        });
//        if (adapter != null) {
//
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


}