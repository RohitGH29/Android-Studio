package com.study.newspadhlo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechFragment extends Fragment {

    String api="17e16f3f7748436ea304457e1da33c27";
    ArrayList<ModalClass> modalClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewoftech;
    private String category="technology";
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.technologyfragment,null);

        progressBar=v.findViewById(R.id.progressBar);

        recyclerViewoftech=v.findViewById(R.id.recyclerviewoftechnology);
        modalClassArrayList=new ArrayList<>();
        recyclerViewoftech.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modalClassArrayList);
        recyclerViewoftech.setAdapter(adapter);
        swipeRefreshLayout=v.findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
                findNews();
            }
        });

        findNews();


        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modalClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Please Check Internet connection!! ",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

            }
        });



    }
}

