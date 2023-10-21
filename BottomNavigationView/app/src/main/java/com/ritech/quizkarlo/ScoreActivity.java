package com.ritech.quizkarlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ritech.quizkarlo.Fragments.LeaderBoardFragment;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.Models.QuestionModel;

import java.util.concurrent.TimeUnit;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreTV, timeTV, totalQTV, correctQTV, wrongQTV , unattemptedQTV;
    private Button leaderB, reAttemptB, viewAnsB;
    private long timeTaken;
    private int finalScore;
    //private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //this line for back button
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        loadData();

        setBookMarks();

        viewAnsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this,AnswersActivity.class);
                startActivity(intent);
            }
        });

        reAttemptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reAttempt();
            }
        });

//        leaderB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //bottomNavigationView.setSelectedItemId(R.id.navigation_leaderBoard);
//              //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LeaderBoardFragment()).commit();
//
//            }
//        });

        saveResult();


    }

    private void init()
    {
        scoreTV = findViewById(R.id.score);
        timeTV = findViewById(R.id.time);
        totalQTV = findViewById(R.id.totalQ);
        correctQTV = findViewById(R.id.correctQ);
        wrongQTV= findViewById(R.id.wrongQ);
        unattemptedQTV = findViewById(R.id.un_attemptedQ);
        //leaderB= findViewById(R.id.leaderB);
        reAttemptB = findViewById(R.id.reattemptB);
        viewAnsB = findViewById(R.id.view_answerB);
        //bottomNavigationView = findViewById(R.id.bottom_navigation);

    }

    private void loadData()
    {
        int correctQ=0 , wrongQ=0 , unattemptQ=0;

        for (int i=0; i<DbQuery.g_quesList.size(); i++)
        {
            if (DbQuery.g_quesList.get(i).getSelectedAns()==-1)
            {
                unattemptQ ++;
            }
            else
            {
                if (DbQuery.g_quesList.get(i).getSelectedAns() == DbQuery.g_quesList.get(i).getcorrectAns())
                {
                    correctQ++;
                }
                else
                {
                    wrongQ++;
                }
            }
        }

        correctQTV.setText(String.valueOf(correctQ));
        wrongQTV.setText(String.valueOf(wrongQ));
        unattemptedQTV.setText(String.valueOf(unattemptQ));

        totalQTV.setText(String.valueOf(DbQuery.g_quesList.size()));

        // Calculate scre
        //finalScore=(correctQ*100)/DbQuery.g_quesList.size();
        finalScore = (correctQ*10);
        scoreTV.setText(String.valueOf(finalScore));

        timeTaken= getIntent().getLongExtra("TIME_TAKEN",0);

        String time = String.format("%02d:%02d min",
                TimeUnit.MILLISECONDS.toMinutes(timeTaken),
                TimeUnit.MILLISECONDS.toSeconds(timeTaken) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeTaken))
        );

        timeTV.setText(time);

    }

    private void reAttempt()
    {
        for (int i=0 ; i<DbQuery.g_quesList.size(); i++)
        {
            DbQuery.g_quesList.get(i).setSelectedAns(-1);
            DbQuery.g_quesList.get(i).setStatus(DbQuery.NOT_VISITED);
        }

        Intent intent = new Intent(ScoreActivity.this,StartTestActivity.class);
        startActivity(intent);
        finish();
    }


    private void saveResult()
    {
      DbQuery.saveResult(finalScore, new MyCompleteListener() {
          @Override
          public void onSuccess() {

             // Toast.makeText(ScoreActivity.this, "success", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onFailure() {

              Toast.makeText(ScoreActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
          }
      });
    }


    private void setBookMarks()
    {
        for (int i=0; i < DbQuery.g_quesList.size(); i++)
        {
            QuestionModel question = DbQuery.g_quesList.get(i);

            if (question.isBookmarked())
            {
                if ( ! DbQuery.g_bmIdList.contains(question.getqID()))
                {
                    DbQuery.g_bmIdList.add(question.getqID());
                    DbQuery.myProfile.setBookmarksCount(DbQuery.g_bmIdList.size());
                }
            }
            else
            {
                if (DbQuery.g_bmIdList.contains(question.getqID()))
                {
                    DbQuery.g_bmIdList.remove(question.getqID());
                    DbQuery.myProfile.setBookmarksCount(DbQuery.g_bmIdList.size());
                }
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home)
        {
            ScoreActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}