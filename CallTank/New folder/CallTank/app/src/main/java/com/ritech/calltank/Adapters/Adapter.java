package com.ritech.calltank.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ritech.calltank.Constant;
import com.ritech.calltank.ModelClass;
import com.ritech.calltank.R;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;
    ArrayList<ModelClass> fileslist;

    public Adapter(Context context, ArrayList<ModelClass> fileslist) {
        this.context = context;
        this.fileslist = fileslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ModelClass modelClass=fileslist.get(position);

        String file = modelClass.getFilename();
        holder.fileName.setText(file);

        Long dateTime = modelClass.getDate();
        String  currentDate = DateFormat.getDateInstance().format(dateTime);
        holder.date.setText(currentDate);

        MediaPlayer mediaPlayer = new MediaPlayer();

        String audioPath = modelClass.getUri().toString();
        try {
            mediaPlayer.setDataSource(audioPath);
        } catch (IOException e) {
            e.printStackTrace();
        }



        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    try {
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mP) {
                                mP.start();
                                holder.play.setVisibility(View.INVISIBLE);
                                holder.pause.setVisibility(View.VISIBLE);
                            }
                        });

                            mediaPlayer.prepare();


                 } catch (IOException e) {
                       e.printStackTrace();
                    }
            }
        });

        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                holder.play.setVisibility(View.VISIBLE);
                holder.pause.setVisibility(View.INVISIBLE);
            }
        });
        

    }

    @Override
    public int getItemCount() {
        return fileslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView play , pause;
        TextView fileName, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            play = itemView.findViewById(R.id.play);
            pause= itemView.findViewById(R.id.pause);
            fileName = itemView.findViewById(R.id.fileName);
            pause.setVisibility(View.INVISIBLE);
            date = itemView.findViewById(R.id.date);

        }
    }
}
