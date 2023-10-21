package com.ritech.quizkarlo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritech.quizkarlo.Adapters.QuestionGridAdapter;
import com.ritech.quizkarlo.Adapters.QuestionsAdapter;

import java.util.concurrent.TimeUnit;

import static com.ritech.quizkarlo.DbQuery.ANSWERED;
import static com.ritech.quizkarlo.DbQuery.NOT_VISITED;
import static com.ritech.quizkarlo.DbQuery.REVIEW;
import static com.ritech.quizkarlo.DbQuery.UNANSWERED;
import static com.ritech.quizkarlo.DbQuery.g_quesList;
import static com.ritech.quizkarlo.DbQuery.g_selected_test_index;
import static com.ritech.quizkarlo.DbQuery.g_testList;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView questionView;
    private TextView tvQuesID, tvTimer, catNameTV;
    private Button submitB, markB, clearSelB , nextB;
    private ImageView quesListB, markImage, bookmarkB;
    private int quesID;
    QuestionsAdapter quesAdapter;
    private DrawerLayout drawer;
  //  private ImageButton drawerCloseB;
    private GridView quesListGV;
    private QuestionGridAdapter gridAdapter;
    private CountDownTimer timer;
    private long timeLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list_layout);
        // hide action bar
        getSupportActionBar().hide();

        findId();

        quesAdapter = new QuestionsAdapter(g_quesList);
        questionView.setAdapter(quesAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        questionView.setLayoutManager(layoutManager);

        gridAdapter = new QuestionGridAdapter(this, g_quesList.size());
        quesListGV.setAdapter(gridAdapter);

        setSnapHelper();

        setClickListeners();

        startTimer();

    }

    public void goToQuestion(int position) {
        questionView.smoothScrollToPosition(position);
        if (drawer.isDrawerOpen(GravityCompat.END))
            drawer.closeDrawer(GravityCompat.END);
    }

    private void startTimer() {
        long totalTime = g_testList.get(g_selected_test_index).getTime() * 60 * 1000;

        timer = new CountDownTimer(totalTime + 1000, 1000) {
            @Override
            public void onTick(long remainigTime) {

                timeLeft = remainigTime;

                @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d min",
                        TimeUnit.MILLISECONDS.toMinutes(remainigTime),
                        TimeUnit.MILLISECONDS.toSeconds(remainigTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainigTime))
                );
                tvTimer.setText(time);
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                long totalTime = g_testList.get(g_selected_test_index).getTime() * 60 * 1000;
                intent.putExtra("TIME_TAKEN", totalTime - timeLeft);
                startActivity(intent);
                QuestionsActivity.this.finish();

            }


        };
        timer.start();
    }

    // on click items
    private void setClickListeners() {

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quesID < g_quesList.size() - 1) {
                    questionView.smoothScrollToPosition(quesID + 1);
                }
                else{
                    submitTest();
                }
            }
        });

        clearSelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                g_quesList.get(quesID).setSelectedAns(-1);
                g_quesList.get(quesID).setStatus(UNANSWERED);
                markImage.setVisibility(View.GONE);
                // to remove the selected option ,refresh recyclerview
                quesAdapter.notifyDataSetChanged();
            }
        });

        quesListB.setOnClickListener(v -> {
            if (!drawer.isDrawerOpen(GravityCompat.END)) {
                gridAdapter.notifyDataSetChanged();
                drawer.openDrawer(GravityCompat.END);
            }

        });

//        drawerCloseB.setOnClickListener(v -> {
//            if (drawer.isDrawerOpen(GravityCompat.END)) {
//                drawer.closeDrawer(GravityCompat.END);
//            }
//        });

        markB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markImage.getVisibility() != View.VISIBLE) {
                    markImage.setVisibility(View.VISIBLE);
                    g_quesList.get(quesID).setStatus(REVIEW);
                } else {
                    markImage.setVisibility(View.GONE);
                    if (g_quesList.get(quesID).getSelectedAns() != -1) {
                        g_quesList.get(quesID).setStatus(ANSWERED);
                    } else {
                        g_quesList.get(quesID).setStatus(UNANSWERED);
                    }
                }
            }
        });

        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTest();
            }
        });

        bookmarkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToBookmark();
            }
        });

    }


    private void submitTest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionsActivity.this);
        builder.setCancelable(true);

        View view = getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);

        Button cancelB = view.findViewById(R.id.cancelB);
        Button confirmB = view.findViewById(R.id.confirmB);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                alertDialog.dismiss();

                Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                long totalTime = g_testList.get(g_selected_test_index).getTime() * 60 * 1000;
                intent.putExtra("TIME_TAKEN", totalTime - timeLeft);
                startActivity(intent);
                QuestionsActivity.this.finish();
            }
        });

        alertDialog.show();

    }


    private void setSnapHelper() {
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(questionView);

        questionView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                View view = snapHelper.findSnapView(recyclerView.getLayoutManager());
                quesID = recyclerView.getLayoutManager().getPosition(view);

                gridAdapter.notifyDataSetChanged();

                if (g_quesList.get(quesID).getStatus() == NOT_VISITED)
                    g_quesList.get(quesID).setStatus(UNANSWERED);

                if (g_quesList.get(quesID).getStatus() == REVIEW) {
                    markImage.setVisibility(View.VISIBLE);
                } else {
                    markImage.setVisibility(View.GONE);
                }


                tvQuesID.setText(String.valueOf(quesID + 1) + "/" + String.valueOf(g_quesList.size()));

                if (g_quesList.get(quesID).isBookmarked())
                {
                    bookmarkB.setImageResource(R.drawable.ic_bookmark_selected);
                }
                else{
                    bookmarkB.setImageResource(R.drawable.ic_bookmark_foreground);
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void findId() {
        questionView = findViewById(R.id.questions_view);
        tvQuesID = findViewById(R.id.tv_quesID);
        tvTimer = findViewById(R.id.tv_timer);
        catNameTV = findViewById(R.id.qa_catName);
        submitB = findViewById(R.id.submitB);
        markB = findViewById(R.id.markB);
        clearSelB = findViewById(R.id.clear_selB);
        nextB = findViewById(R.id.next_quesB);
        quesListB = findViewById(R.id.ques_list_gridB);
        drawer = findViewById(R.id.drawer_layout);
      //  drawerCloseB = findViewById(R.id.drawerCloseB);
        markImage = findViewById(R.id.mark_image);
        quesListGV = findViewById(R.id.ques_list_gv);
        bookmarkB = findViewById(R.id.qa_bookmarkB);

        quesID = 0;
        tvQuesID.setText("1/" + String.valueOf(g_quesList.size()));
        catNameTV.setText(DbQuery.g_catList.get(DbQuery.g_selected_cat_index).getName());

        g_quesList.get(0).setStatus(NOT_VISITED);

        if (g_quesList.get(0).isBookmarked())
        {
            bookmarkB.setImageResource(R.drawable.ic_bookmark_selected);
        }
        else{
            bookmarkB.setImageResource(R.drawable.ic_bookmark_foreground);
        }


    }


    private void addToBookmark() {

        if (g_quesList.get(quesID).isBookmarked())
        {
            g_quesList.get(quesID).setBookmarked(false);
            bookmarkB.setImageResource(R.drawable.ic_bookmark_foreground);
        }
        else
        {
            g_quesList.get(quesID).setBookmarked(true);
            bookmarkB.setImageResource(R.drawable.ic_bookmark_selected);
        }

    }

}