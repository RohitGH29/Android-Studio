package com.ritech.textspeech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button btn;


    TextToSpeech textToSpeech;
    SpeechRecognizer speechRecognizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);
        btn = findViewById(R.id.button);

        // check permission of audio recording
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }


        textToSpeech = new TextToSpeech(getApplicationContext()
                , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i== TextToSpeech.SUCCESS){
                    // select language
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        Intent speechRecognizerIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text value
                String s = txt.getText().toString();

                // text convert to speech
                int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        speechRecognizer.startListening(speechRecognizerIntent);
                         Toast.makeText(MainActivity.this, "Says", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);

            }
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

              //  Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
              //  int ab = Integer.parseInt(String.valueOf(results));
                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                //String c = results.getString(speechRecognizer.RESULTS_RECOGNITION);
                int a = 4;
                String ab = String.valueOf(a);
                String abc = String.valueOf(data.get(0));

              // Toast.makeText(MainActivity.this, abc, Toast.LENGTH_SHORT).show();

                if (abc.equals(ab))
                {
                    int s1 = textToSpeech.speak("Correct",TextToSpeech.QUEUE_FLUSH,null);
                  //  Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    int s2 = textToSpeech.speak("Incorrect",TextToSpeech.QUEUE_FLUSH,null);
                   // Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();

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