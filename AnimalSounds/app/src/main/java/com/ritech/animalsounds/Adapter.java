package com.ritech.animalsounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<Modal> animalArrayList;

    public Adapter(Context context, ArrayList<Modal> animalArrayList) {
        this.context = context;
        this.animalArrayList = animalArrayList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        Modal animal = animalArrayList.get(position);

        holder.imageView.setImageResource(animal.image);
      //  holder.imgText.setText(animal.name);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(holder.imageView.getContext(), animal.audio);
                mediaPlayer.start();

                //animalArrayList.lastIndexOf(position);

             //  String i = (animalArrayList.get(position));
                //Log.e("myview", String.valueOf(animalArrayList.size()));
              //  Log.e("myview", String.valueOf(animal));

            }
        });
    }

    @Override
    public int getItemCount()    {
        return animalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
       // TextView imgText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgItem);
            //imgText=itemView.findViewById(R.id.imgName);
        }
    }
}
