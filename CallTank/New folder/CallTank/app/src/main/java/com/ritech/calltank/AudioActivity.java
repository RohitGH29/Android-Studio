package com.ritech.calltank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ritech.calltank.Adapters.Adapter;
import com.ritech.calltank.Adapters.SlideAdapter;

import java.io.File;
import java.util.ArrayList;

public class AudioActivity extends AppCompatActivity {

    //Adapter adapter;
    RecyclerView recyclerView;

   // ModelClass f;

    File[] files;
    ArrayList<ModelClass> fileslist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Toolbar toolbar = findViewById(R.id.aa_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Call Tank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        setuplayout();
        diplay();

    }

    private void setuplayout() {

      //  fileslist.clear();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

       /* adapter = new Adapter(AudioActivity.this, getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); */

    }

    public ArrayList<ModelClass> findSong(File file){

        ArrayList<ModelClass> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for (File singleFile: files)
        {
            if (singleFile.isDirectory() && !singleFile.isHidden()){

                arrayList.addAll(findSong(singleFile));
            }
            else
            {
                if (singleFile.getName().endsWith(".mp3")  ||
                singleFile.getName().endsWith(".wav")){

                    arrayList.add(singleFile);
            }
            }
        }

        return arrayList;
    }

    void diplay(){

        final ArrayList<ModelClass> mySongs = findSong(Environment.getExternalStorageDirectory());

        files= new String[mySongs.size()];

        for (int i=0 ; i<mySongs.size() ; i++)
        {
            files[i] = mySongs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");
            fileslist.add(files);
        }

       Adapter adapter = new Adapter(this,fileslist);
        recyclerView.setAdapter(adapter);
       // adapter.notifyDataSetChanged();

    }
   // public ArrayList<ModelClass> getData() {
    //    ModelClass f;
       // String targetpath = Environment.getExternalStorageDirectory().getAbsolutePath() ;
      //  File targetdir = new File(targetpath);
       // files = targetdir.listFiles();
      //  files = Environment.getExternalStorageDirectory();
       // if (files.length != 0) {
         //   for (int i = files.length -1 ; i > -1; i--) {
           //     File file = files[i];
           //     f = new ModelClass();
            //    f.setUri(Uri.fromFile(file));
             /*   f.setPath(files[i].getAbsolutePath());
                f.setDate((file.lastModified()));
                f.setFilename(file.getName());
                if (!f.getUri().toString().endsWith(".nomedia")) {
                    fileslist.add(f);
                }
            }
        } else {
            Toast.makeText(this, "Make dummy call", Toast.LENGTH_SHORT).show();
            dummyCallAlert();
        }

        return fileslist;
    }*/

    private void dummyCallAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AudioActivity.this);
        builder.setCancelable(true);

        View view = getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);

        Button confirmB = view.findViewById(R.id.confirmB);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();


        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + 91));//change the number
                startActivity(callIntent);
            }
        });

        alertDialog.show();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        return super.onOptionsItemSelected(item);
    }


}