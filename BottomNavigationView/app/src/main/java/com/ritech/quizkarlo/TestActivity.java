package com.ritech.quizkarlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ritech.quizkarlo.Adapters.TestAdapter;
import com.ritech.quizkarlo.Interface.MyCompleteListener;

public class TestActivity extends AppCompatActivity {

    private RecyclerView testView;
    private Toolbar toolbar;

    private TestAdapter adapter;
    private Dialog progressDialog;
    private TextView dialogText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(DbQuery.g_catList.get(DbQuery.g_selected_cat_index).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog = new Dialog(TestActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogText = progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("Loading....");
        progressDialog.show();


        testView = findViewById(R.id.test_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testView.setLayoutManager(layoutManager);

       // loadTestData();

        // Fetch data from firebas eof test Activity
        DbQuery.loadTestData(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                DbQuery.loadMyScores(new MyCompleteListener() {
                    @Override
                    public void onSuccess() {
                        adapter = new TestAdapter(DbQuery.g_testList);
                        testView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onFailure() {
                        progressDialog.dismiss();
                        Toast.makeText(TestActivity.this, "Something went Wrong !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onFailure() {
                progressDialog.dismiss();
                Toast.makeText(TestActivity.this, "Something went Wrong !", Toast.LENGTH_SHORT).show();
            }
        });



    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home)
        {
            TestActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}