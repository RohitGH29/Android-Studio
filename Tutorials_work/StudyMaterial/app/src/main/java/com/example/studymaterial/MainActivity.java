package com.example.studymaterial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "MainActivity";
//
//    public static final String KEY_TITLE = "title";
//    public static final String KEY_DESCRIPTION = "description";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPriority;
    private EditText editEditTags;
    private TextView textViewData;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");

    private DocumentSnapshot lastResult;
    // private DocumentReference noteRef = db.document("Notebook/My First Note");  // this is the path of database where data stored.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        textViewData = findViewById(R.id.text_view_data);
        editEditTags = findViewById(R.id.edit_text_tags);
        editTextPriority = findViewById(R.id.edit_text_priority);

        //executeBatchedWrite();
        // executeTransaction();
        //   updateArray();   // this is used to update the array of tags

    }

  /*  private void updateArray() {
//        notebookRef.document("Jddt6gMuGKzdLLQvvPOn")
//               // .update("tags", FieldValue.arrayUnion("new tag"));
//                .update("tags",FieldValue.arrayRemove("new tag"));
        notebookRef.document("3CmbPINoIHf8Fx1FeL2X")
                // .update("tags", FieldValue.arrayUnion("new tag"));
                .update("tags.tag1",false);
    } */

  /*  private void executeTransaction() {



       db.runTransaction(new Transaction.Function<Long>() {

            @Override
            public Long apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentReference exampleNotRef = notebookRef.document("New Note");
                DocumentSnapshot exampleNoteSnapshot = transaction.get(exampleNotRef);
                long newPiority = exampleNoteSnapshot.getLong("priority") + 1;
                transaction.update(exampleNotRef, "priority", newPiority);
                return newPiority;
            }
        }).addOnSuccessListener(new OnSuccessListener<Long>() {
            @Override
            public void onSuccess(Long result) {
                Toast.makeText(MainActivity.this, "New priority: " + result, Toast.LENGTH_SHORT).show();
            }
        }); } */



 /*   private void executeBatchedWrite() {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        WriteBatch batch = db.batch();
        DocumentReference doc1 = notebookRef.document("New Note");
        batch.set(doc1, new Note("New Note", "New note", 1));

        DocumentReference doc2 = notebookRef.document("9HPZcjNWGT6waJrUo9UU");
        batch.update(doc2, "title", "updated note");

        DocumentReference doc3 = notebookRef.document("uzKmpXwZlA26RsVB07s1");
        batch.delete(doc3);

        DocumentReference doc4 = notebookRef.document();
        batch.set(doc4, new Note("Added note", "des", 2));

        batch.commit().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });

    } */

   /* @Override
    protected void onStart() {
        super.onStart();

        notebookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d("mytag", e.toString());
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) { // this line is used to collect multiple data from database
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());
                    String documentId = note.getDocumentId();
                    String title = note.getTitle();
                    String description = note.getDescription();
                    int priority = note.getPriority();

                    data += "ID: " + documentId + "\nTitle: " + title +
                            "\nDescription: " + description + "\nPriority: " + priority + "\n\n";


                }
                textViewData.setText(data);
            }
        });

       /* noteRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d("mytag", e.toString());
                    return;
                }
                if (documentSnapshot.exists()) {
                    Note note = documentSnapshot.toObject(Note.class);
                    String title = note.getTitle();
                    String description = note.getDescription();

                    textViewData.setText("Title: " + title + "\n Description: " + description);
                } else {
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }  */

 /*   @Override
    protected void onStart() {
        super.onStart();
        notebookRef
                .addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            DocumentSnapshot documentSnapshot = dc.getDocument();
                            String id = documentSnapshot.getId();
                            int oldIndex = dc.getOldIndex();
                            int newIndex = dc.getNewIndex();

                            switch (dc.getType()) {
                                case ADDED:
                                    textViewData.append("\nAdded: " + id +
                                            "\nOld Index: " + oldIndex + "New Index: " + newIndex);
                                    break;
                                case MODIFIED:
                                    textViewData.append("\nModified: " + id +
                                            "\nOld Index: " + oldIndex + "New Index: " + newIndex);
                                    break;
                                case REMOVED:
                                    textViewData.append("\nRemove: " + id +
                                            "\nOld Index: " + oldIndex + "New Index: " + newIndex);
                                    break;
                            }
                        }

                    }
                });
    }*/


    // this is use to add data in the database
    public void add(View view) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        //   String documentId =notebookRef.document("ai");
        if (editTextPriority.length() == 0) {
            editTextPriority.setText("0");
        }
        int priority = Integer.parseInt(editTextPriority.getText().toString());
//        Map<String, Object> note = new HashMap<>();
//        note.put(KEY_TITLE, title);
//        note.put(KEY_DESCRIPTION, description);

        String tagInput = editEditTags.getText().toString();
        String[] tagArray = tagInput.split("\\s*,\\s*");
        Map<String, Boolean> tags = new HashMap<>();

        for (String tag : tagArray) {  ///this is used to store the tags in map for nested loop
            tags.put(tag, true);
        }

        // List<String> tags = Arrays.asList(tagArray);

        Note note = new Note(title, description, priority, tags); // call the note class

       // notebookRef.add(note); // this line is used to store data in collection

        notebookRef.document("3CmbPINoIHf8Fx1FeL2X").collection("Custom note").add(note);

       /* noteRef.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        Log.e("mytag", "onFailure: " + e.toString());
                    }
                });*/
    }

    /// this is use to retrieve data from data base
    public void load(View v) {
        notebookRef
                .document("3CmbPINoIHf8Fx1FeL2X")
                .collection("Custom note")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());

                            String documentId = note.getDocumentId();

                            data += "Id: " + documentId;

//                            for (String tag : note.getTags()) {  // this is use for getting all the tags
//                                data += "\n-" + tag;
//                            }
                            for (String tag : note.getTags().keySet()) {  // this is use for getting all the tags
                                data += "\n-" + tag;
                            }

                            data += "\n\n";

                        }

                        textViewData.setText(data);

                    }
                });

//        Query query;
//        if (lastResult == null) {
//            query = notebookRef.orderBy("priority")
//                    .limit(3);
//        } else {
//            query = notebookRef.orderBy("priority")
//                    .startAfter(lastResult)
//                    .limit(3);
//        }
//        notebookRef.document("2Y4wrGMM5vXw4zsTSvab")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        notebookRef.orderBy("priority")
//                                .startAt(3)
//        query.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        String data = "";
//
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Note note = documentSnapshot.toObject(Note.class);
//                            note.setDocumentId(documentSnapshot.getId());
//
//                            String documentId = note.getDocumentId();
//                            String title = note.getTitle();
//                            String description = note.getDescription();
//                            int priority = note.getPriority();
//
//                            data += "ID: " + documentId + "\nTitle: " + title + "\nDescription: " + description
//                                    + "\nPriority: " + priority + "\n\n";
//                        }
//                        if (queryDocumentSnapshots.size() > 0) {
//                            data += "_________\n\n";
//                            textViewData.append(data);
//
//                            lastResult = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);
//                        }
//                    }
//                });
////                    }
//////                });


//        notebookRef.orderBy("priority")
//                .startAt(3)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        String data = "";
//
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Note note = documentSnapshot.toObject(Note.class);
//                            note.setDocumentId(documentSnapshot.getId());
//
//                            String documentId = note.getDocumentId();
//                            String title = note.getTitle();
//                            String description = note.getDescription();
//                            int priority = note.getPriority();
//
//                            data += "ID: " + documentId + "\nTitle: " + title + "\nDescription: " + description
//                                    + "\nPriority: " + priority + "\n\n";
//                        }
//                        textViewData.setText(data);
//                    }
//                });

//        // part 11 - merge tasks to create queries
//        Task task1 = notebookRef.whereLessThan("priority", 2)
//                .orderBy("priority")
//                .get();
//        Task task2 = notebookRef.whereGreaterThan("priority", 2)
//                .orderBy("priority")
//                .get();
//
//        Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(task1, task2);
//        allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
//            @Override
//            public void onSuccess(List<QuerySnapshot> querySnapshots) {
//                String data = "";
//                for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
//                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) { // this line is used to collect multiple data from database
//                        Note note = documentSnapshot.toObject(Note.class);
//                        note.setDocumentId(documentSnapshot.getId());
//
//                        String documentId = note.getDocumentId();
//                        String title = note.getTitle();
//                        String description = note.getDescription();
//                        int priority = note.getPriority();
//
//                        data += "ID: " + documentId + "\nTitle: " + title +
//                                "\nDescription: " + description + "\nPriority: " + priority + "\n\n";
//
//                    }
//                }
//                textViewData.setText(data);
//            }
//        });

        // this is collect the data from root node
//        notebookRef.whereEqualTo("priority",2) // this is use to search data for condition
//               // .orderBy("priority", Query.Direction.ASCENDING)// here the data come in ascending order
//                .orderBy("priority")
//                .whereEqualTo("title","a")
//               // .limit(1)// this is used to show only limited data
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Toast.makeText(MainActivity.this, "ADAD", Toast.LENGTH_SHORT).show();
//                        String data = "";
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) { // this line is used to collect multiple data from database
//                            Note note = documentSnapshot.toObject(Note.class);
//                            note.setDocumentId(documentSnapshot.getId());
//
//                            String documentId = note.getDocumentId();
//                            String title = note.getTitle();
//                            String description = note.getDescription();
//                            int priority = note.getPriority();
//
//                            data += "ID: " + documentId + "\nTitle: " + title +
//                                    "\nDescription: " +  description + "\nPriority: " + priority +"\n\n";
//
//                        }
//                        textViewData.setText(data);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "onFailure: "+e.toString());
//                    }
//                });

       /* noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
//                            String title = documentSnapshot.getString(KEY_TITLE);
//                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
                            Note note = documentSnapshot.toObject(Note.class);
                            String title = note.getTitle();
                            String description = note.getDescription();

                            textViewData.setText("Title: " + title + "\n Description: " + description);
                        } else {
                            Toast.makeText(MainActivity.this, "Document not exits", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail to load", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

  /*  public void updateDescription(View v) {
        String description = editTextDescription.getText().toString();
        noteRef.update(KEY_DESCRIPTION, description); // this is use to update the description in database
    }

    public void deleteDescription(View v) {
//        Map<String,Object> note  = new HashMap<>();
//        note.put(KEY_DESCRIPTION, FieldValue.delete());
//        noteRef.update(note);
        noteRef.update(KEY_DESCRIPTION, FieldValue.delete());
    }

    public void deleteNote(View v) {
        noteRef.delete();
    } */

}