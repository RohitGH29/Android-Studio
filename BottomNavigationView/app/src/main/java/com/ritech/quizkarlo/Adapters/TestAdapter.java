package com.ritech.quizkarlo.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.Models.TestModal;
import com.ritech.quizkarlo.R;
import com.ritech.quizkarlo.StartTestActivity;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<TestModal> testList;

    public TestAdapter(List<TestModal> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position)
    {
        int progress = testList.get(position).getTopScore();

       holder.setData(position,progress);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testNo;
        private TextView topScore;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            testNo = itemView.findViewById(R.id.testNO);
            topScore = itemView.findViewById(R.id.scoretext);
            progressBar = itemView.findViewById(R.id.testProgressbar);


        }

        private void setData(int pos , int progress)
        {
          testNo.setText("Test No : " + String.valueOf(pos+1));
          topScore.setText(String.valueOf(progress) + "%");
          progressBar.setProgress(progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // collect test no.
                    DbQuery.g_selected_test_index = pos;

                    Intent intent = new Intent(itemView.getContext(), StartTestActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }


}
