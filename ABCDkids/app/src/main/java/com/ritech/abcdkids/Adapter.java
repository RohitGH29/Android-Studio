package com.ritech.abcdkids;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    Context context;
    ArrayList<Modal> newsArray;

    public Adapter(Context context, ArrayList<Modal> newsArray) {

        this.context=context;
        this.newsArray= newsArray ;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Modal news = newsArray.get(position);

        holder.alphaimg.setImageResource(news.image);
        // Log.d("click","Clicked");
        holder.alphaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(holder.alphaimg.getContext(), news.audio);
                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView alphaimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            alphaimg = itemView.findViewById(R.id.imgItem);

        }
    }
}
