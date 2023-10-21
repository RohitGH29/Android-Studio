package com.ritech.quizapp;

import static com.ritech.quizapp.MainActivity.listOfQ;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;
    List<ModalClass> allQuestionsList;
    ModalClass modalClass;
    int index = 0;
    CardView cardOA, cardOB, cardOC, cardOD;
    TextView cardQuestion, optionA, optionB, optionC, optionD;
    ImageView imageView;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       // getSupportActionBar().hide();

        Hooks();

        allQuestionsList = listOfQ;
        Collections.shuffle(allQuestionsList);
        modalClass = listOfQ.get(index);


        SetAllData();

        nextBtn.setClickable(false);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_try_again).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();
    }

    private void SetAllData() {

        cardQuestion.setText(modalClass.getQuestion());
        optionA.setText(modalClass.getoA());
        optionB.setText(modalClass.getoB());
        optionC.setText(modalClass.getoC());
        optionD.setText(modalClass.getoD());

    }

    private void Hooks() {
        progressBar = findViewById(R.id.quiz_timer);
        cardQuestion = findViewById(R.id.card_question);
        optionA = findViewById(R.id.card_optiona);
        optionB = findViewById(R.id.card_optionb);
        optionC = findViewById(R.id.card_optionc);
        optionD = findViewById(R.id.card_optiond);

        cardOA = findViewById(R.id.card_view_oA);
        cardOB = findViewById(R.id.card_view_oB);
        cardOC = findViewById(R.id.card_view_oC);
        cardOD = findViewById(R.id.card_view_oD);
        imageView=findViewById(R.id.card_image);

        nextBtn = findViewById(R.id.next_btn);
    }

    public void Correct(CardView cardView) {
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerValue = 20;
                countDownTimer.cancel();
                countDownTimer.start();
                nextBtn.setClickable(false);
                correctCount++;
                enableButton();
                if (index < listOfQ.size() - 1) {
                    index++;
                    modalClass = listOfQ.get(index);
                    SetAllData();
                    resetColor();
                } else {
                    GameWon();
                }
            }
        });

    }

    public void Wrong(CardView cardOA) {
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerValue = 20;
                countDownTimer.cancel();
                countDownTimer.start();
                nextBtn.setClickable(false);
                wrongCount++;
                enableButton();
                if (index < listOfQ.size() - 1) {
                    index++;
                    modalClass = listOfQ.get(index);
                    SetAllData();
                    resetColor();
                } else {
                    GameWon();
                }
            }
        });

    }


    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this, WonActivity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);
    }

    public void enableButton() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);

    }

    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor() {
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modalClass.getoA().equals(modalClass.getAns())) {
            Correct(cardOA);
        } else {
            Wrong(cardOA);
        }
    }

    public void optionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modalClass.getoB().equals(modalClass.getAns())) {
            Correct(cardOB);
        } else {
            Wrong(cardOB);
        }
    }

    public void optionCClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modalClass.getoC().equals(modalClass.getAns())) {
            Correct(cardOC);
        } else {
            Wrong(cardOC);
        }
    }

    public void optionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modalClass.getoD().equals(modalClass.getAns())) {
            Correct(cardOD);
        } else {
            Wrong(cardOD);
        }
    }


}