package com.example.demoanimalsound;

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
    ArrayList<Animal> animalArrayList;

    public Adapter(Context context, ArrayList<Animal> animalArrayList) {
        this.context = context;
        this.animalArrayList = animalArrayList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homescreen,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        Animal animal = animalArrayList.get(position);

        holder.imageView.setImageResource(animal.image);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(holder.imageView.getContext(), animal.audio);
                mediaPlayer.start();
            }
        });


    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.imgItem);
        }
    }
}
