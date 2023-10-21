package com.ritech.mcqquestionpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder>{

    Context context;
    ArrayList<Modal> newsArray;

    public myAdapter(Context context, ArrayList<Modal> newsArray) {
        this.context = context;
        this.newsArray = newsArray;
    }

    @NonNull
    @Override
    public myAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.MyViewHolder holder, int position) {
        Modal ques = newsArray.get(position);

        holder.quesNo.setText(ques.ques);


    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quesNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quesNo= itemView.findViewById(R.id.questionNo);
        }
    }
}
