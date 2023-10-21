package com.ritech.spaceshooter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp extends AppCompatActivity {

    ImageView privacy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        privacy = findViewById(R.id.privacyBtn);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://privacypolicy2910.blogspot.com/2022/04/space-shooter-alien-shooter.html");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);

            }
        });
    }



    public void startGame(View view){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
