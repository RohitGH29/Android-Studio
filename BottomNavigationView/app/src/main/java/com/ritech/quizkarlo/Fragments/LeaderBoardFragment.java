package com.ritech.quizkarlo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ritech.quizkarlo.Adapters.RankAdapter;
import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.MainActivity;
import com.ritech.quizkarlo.R;

import static com.ritech.quizkarlo.DbQuery.g_selected_test_index;
import static com.ritech.quizkarlo.DbQuery.g_usersCount;
import static com.ritech.quizkarlo.DbQuery.g_usersList;
import static com.ritech.quizkarlo.DbQuery.myPerformance;

public class LeaderBoardFragment extends Fragment {

    private TextView totalUsersTV, myImgTextTV, myScoreTV, myRankTV ;
    private RecyclerView usersView;
    private RankAdapter adapter;
    //private Dialog progressDialog;
    //private TextView dialogText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_leaderboard,container,false);

       ((MainActivity)getActivity()).getSupportActionBar().setTitle("LeaderBoard");

       initViews(view);

        // progressDialog = new Dialog(getContext());
        // progressDialog.setContentView(R.layout.dialog_layout);
        // progressDialog.setCancelable(false);
        // progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        // dialogText=progressDialog.findViewById(R.id.dialog_text);
        // dialogText.setText("Loading....");
        // progressDialog.show();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        usersView.setLayoutManager(layoutManager);

        adapter = new RankAdapter(g_usersList);

        usersView.setAdapter(adapter);

        DbQuery.getTopUsers(new MyCompleteListener() {
            @Override
            public void onSuccess() {

                adapter.notifyDataSetChanged();

                if (DbQuery.myPerformance.getScore() != 0)
                {
                    if ( ! DbQuery.isMeOnTopList)
                    {
                        calculateRank();
                    }

                    myScoreTV.setText("Score : " + DbQuery.myPerformance.getScore());
                    myRankTV.setText("Rank : " + DbQuery.myPerformance.getRank());

                }

                // progressDialog.show():

            }

            @Override
            public void onFailure() {

                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                // progressDialog.show():
            }
        });

        totalUsersTV.setText("Total Users : " + DbQuery.g_usersCount);
        myImgTextTV.setText(myPerformance.getName().toUpperCase().substring(0,1));

       return view;
    }


    private void initViews(View view)
    {
        totalUsersTV = view.findViewById(R.id.total_users);
        myImgTextTV = view.findViewById(R.id.img_text);
        myScoreTV = view.findViewById(R.id.total_score);
        myRankTV = view.findViewById(R.id.rank);
        usersView = view.findViewById(R.id.users_view);
    }


    private void calculateRank()
    {
        int lowTopScore = g_usersList.get(g_usersList.size() -1 ).getScore();

        int remaining_slots = g_usersCount - 20;

        int mySlot = (myPerformance.getScore()*remaining_slots)/ lowTopScore;

        int rank;

        if (lowTopScore != myPerformance.getScore())
        {
            rank = g_usersCount - mySlot;
        }
        else
        {
            rank = 21;
        }

        myPerformance.setRank(rank);

    }


}

