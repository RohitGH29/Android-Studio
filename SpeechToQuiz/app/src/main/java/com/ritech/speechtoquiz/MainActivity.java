package com.ritech.speechtoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button btn;
    int round=0,a;
    ArrayList<String> result = new ArrayList<String>();


    TextToSpeech textToSpeech;
    SpeechRecognizer speechRecognizer;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txt = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setText("Start");



        // check permission of audio recording
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }


        textToSpeech = new TextToSpeech(getApplicationContext()
                , i -> {
                    if (i== TextToSpeech.SUCCESS){
                        // select language
                        int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                    }
                });


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        Intent speechRecognizerIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


        btn.setOnClickListener(view -> {
            if (round==0){
                txt.setText("5*7=");
                a = 35;
            }
            if (round ==1){
                txt.setText("7*2=");
                a = 14;
            }
            if (round==2)
            {
                txt.setText("3*4=");
                a = 12;
            }if (round==3)
            {
                txt.setText("5*4=");
                a = 20;
            }if (round==4)
            {
                txt.setText("3*8=");
                a = 24;
            }if (round==5)
            {
                txt.setText("9*2=");
                a = 18;
            }if (round==6)
            {
                txt.setText("5*6=");
                a = 30;
            }if (round==7)
            {
                txt.setText("9*6=");
                a = 54;
            }if (round==8)
            {
                txt.setText("8*4=");
                a = 32;
            }if (round==9)
            {
                txt.setText("3*7=");
                a = 21;
            }
            if (round==10){
                btn.setVisibility(View.INVISIBLE);
                txt.setText("Your Answer");
                LinearLayout rootView=(LinearLayout) findViewById(R.id.rootView);

                int i=0;
                while (i<result.size())
                {
                    TextView wordsView=new TextView(this);
                    wordsView.setTextColor(R.color.black);
                    wordsView.setTextSize(20);
                    wordsView.setText(result.get(i));
                    rootView.addView(wordsView);
                   // txt.setText(txt +"\n"+ result.get(i));
                    Log.d("its", result.get(i));
                    i++;
                }

            }

            // Get text value
            String s = txt.getText().toString();

            // text convert to speech
            int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);

            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    if (round==10){
                        speechRecognizer.stopListening();
                    }
                       else {
                        AudioManager audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);

                        speechRecognizer.startListening(speechRecognizerIntent);
                        // Toast.makeText(MainActivity.this, "Says", Toast.LENGTH_SHORT).show();
                    }
                }
            }, 1200);

        });



        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                // Toast.makeText(MainActivity.this, "Ready", Toast.LENGTH_SHORT).show();
                // int s1 = textToSpeech.speak("Stop",TextToSpeech.QUEUE_FLUSH,null);
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle results) {

                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String abc = String.valueOf(data.get(0));

                result.add(abc);
                Log.d("its", result.get(round));


                String ab = String.valueOf(a);
                //Toast.makeText(MainActivity.this, abc, Toast.LENGTH_SHORT).show();

                if (abc.equals(ab))
                {
                    int s1 = textToSpeech.speak("Correct",TextToSpeech.QUEUE_FLUSH,null);
                    round++;
                    btn.setText("Next");
                    //  Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    int s2 = textToSpeech.speak("Incorrect",TextToSpeech.QUEUE_FLUSH,null);
                    round++;
                    btn.setText("Next");
                }



            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {     super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }



}