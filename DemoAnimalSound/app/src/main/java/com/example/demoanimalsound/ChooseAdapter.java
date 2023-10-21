package com.example.demoanimalsound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {


    Context context;
    ArrayList<Choose> chooseArrayList;

    public ChooseAdapter(Context context, ArrayList<Choose> chooseArrayList) {
        this.context = context;
        this.chooseArrayList = chooseArrayList;
    }

    @NonNull
    @Override
    public ChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.choose_item_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAdapter.ViewHolder holder, int position) {
       Choose choose= chooseArrayList.get(position);
       holder.chooseItemImg.setImageResource(choose.imgChoose);

           holder.chooseItemImg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
          Intent intent = new Intent(holder.chooseItemImg.getContext(),MainActivity.class);
         // holder.chooseItemImg.startActivities(intent);

               }
           });


    }

    @Override
    public int getItemCount() {
        return chooseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView chooseItemImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chooseItemImg=itemView.findViewById(R.id.chooseImg);
        }
    }
}
