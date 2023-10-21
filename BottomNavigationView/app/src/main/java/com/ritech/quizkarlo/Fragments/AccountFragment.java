package com.ritech.quizkarlo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ritech.quizkarlo.BookMarksActivity;
import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.Login.LoginActivity;
import com.ritech.quizkarlo.MainActivity;
import com.ritech.quizkarlo.MyProfileActivity;
import com.ritech.quizkarlo.R;

import static com.ritech.quizkarlo.DbQuery.g_usersCount;
import static com.ritech.quizkarlo.DbQuery.g_usersList;
import static com.ritech.quizkarlo.DbQuery.myPerformance;

public class AccountFragment extends Fragment {

    private LinearLayout logoutB, leaderB, profileB, bookmarksB;
    private TextView profile_img_text, name, score, rank;
    private BottomNavigationView bottomNavigationView;
    //private Dialog progressDialog;
    //private TextView dialogText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        initviews(view);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("My Account");

        String userName = DbQuery.myProfile.getName();
        profile_img_text.setText(userName.toUpperCase().substring(0,1));

        name.setText(userName);

        score.setText(String.valueOf(DbQuery.myPerformance.getScore()));

        if (DbQuery.g_usersList.size() == 0)
        {
            // progressDialog.show():
            DbQuery.getTopUsers(new MyCompleteListener() {
                @Override
                public void onSuccess() {

                    if (DbQuery.myPerformance.getScore() != 0)
                    {
                        if ( ! DbQuery.isMeOnTopList)
                        {
                            calculateRank();
                        }

                        score.setText("Score : " + DbQuery.myPerformance.getScore());
                        rank.setText("Rank : " + DbQuery.myPerformance.getRank());

                    }

                    // progressDialog.show():

                }

                @Override
                public void onFailure() {

                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    // progressDialog.show():
                }
            });
        }
        else
        {
            score.setText("Score : " + DbQuery.myPerformance.getScore());
            if (myPerformance.getScore() != 0)
            rank.setText("Rank : " + DbQuery.myPerformance.getRank());
        }



        // logout button
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code for manually sign in
                FirebaseAuth.getInstance().signOut();

                // When user sign in by google
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                GoogleSignInClient mGoogleClient = GoogleSignIn.getClient(getContext(), gso);
                mGoogleClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        getActivity().finish();

                    }
                });

            }
        });

        // bookmark
        bookmarksB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),BookMarksActivity.class);
                startActivity(intent);
            }
        });

        profileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), MyProfileActivity.class);
                startActivity(intent);
            }
        });

        leaderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_leaderBoard);
            }
        });


        return view;
    }

    private void initviews(View view) {
        logoutB = view.findViewById(R.id.logoutB);
        profile_img_text = view.findViewById(R.id.profile_img_text);
        name = view.findViewById(R.id.name);
        score = view.findViewById(R.id.total_score);
        rank = view.findViewById(R.id.rank);
        bookmarksB = view.findViewById(R.id.bookmarkB);
        leaderB = view.findViewById(R.id.leaderB);
        profileB = view.findViewById(R.id.profileB);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

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
