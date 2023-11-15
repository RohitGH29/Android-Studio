package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrContact = new ArrayList<>();
    FloatingActionButton btnOpenDialog;
    RecyclerView recyclerView;
    RecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        // set recyclerview layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create contact database
        contactDB();


        // now adapter calling
        adapter = new RecyclerContactAdapter(this, arrContact);
        recyclerView.setAdapter(adapter);


        // this btn is used to add new contact
        addBtn();


    }

    private void contactDB() {
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "A", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "B", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "C", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "D", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "E", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "F", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "G", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "H", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "I", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
        arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground, "J", "8282828676"));
    }

    private void addBtn() {

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);
                // finding ids of elements
                EditText edtName = dialog.findViewById(R.id.nameEditText);
                EditText edtNumber = dialog.findViewById(R.id.numberEditText);
                Button btnAction = dialog.findViewById(R.id.submitButton);
                // on submit button click
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", number = "";
                        if (!(edtName.getText().toString().equals("")) && !(edtNumber.getText().toString().equals(""))) {
                            name = edtName.getText().toString();
                            number = edtNumber.getText().toString();
                            arrContact.add(new ContactModel(R.drawable.ic_launcher_foreground,name, number));
                            adapter.notifyItemInserted(arrContact.size() - 1);
                            recyclerView.scrollToPosition(arrContact.size() - 1);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Fill the name", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                dialog.show();

            }
        });
    }
}