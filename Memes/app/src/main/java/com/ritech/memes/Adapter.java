package com.ritech.memes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {


        String url = modelArrayList.get(position).getUrl();
        holder.setImage(url);





        holder.sahreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharing = new Intent(Intent.ACTION_SEND);
                sharing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sharing.setType("text/plain");
                String subject = "Hey Man just look at this Meme app " + url ;
                sharing.putExtra(Intent.EXTRA_TEXT,subject);
                context.startActivity(Intent.createChooser(sharing,"Sharing using"));
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        LinearLayout sahreLayout;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            sahreLayout = itemView.findViewById(R.id.share_layout);

        }

        void setImage(String link){
            Glide.with(context).load(link).into(imageView);
        }

    }

}
