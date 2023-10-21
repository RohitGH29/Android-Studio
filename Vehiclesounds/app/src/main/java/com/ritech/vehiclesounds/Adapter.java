package com.ritech.vehiclesounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<Modal> vehicleArrayList;

    public Adapter(Context context, ArrayList<Modal> vehicleArrayList) {
        this.context = context;
        this.vehicleArrayList = vehicleArrayList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        Modal animal = vehicleArrayList.get(position);

        holder.imageView.setImageResource(animal.image);
        holder.imgText.setText(animal.name);


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
    public int getItemCount()    {
        return vehicleArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView imgText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgItem);
            imgText=itemView.findViewById(R.id.imgName);
        }
    }
}

