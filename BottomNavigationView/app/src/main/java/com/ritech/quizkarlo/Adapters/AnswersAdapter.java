package com.ritech.quizkarlo.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritech.quizkarlo.Models.QuestionModel;
import com.ritech.quizkarlo.R;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<QuestionModel> quesList;

    public AnswersAdapter(List<QuestionModel> quesList) {
        this.quesList = quesList;
    }

    @NonNull

    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item_layout,parent,false);

        return new AnswersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AnswersAdapter.ViewHolder holder, int position) {

        String ques = quesList.get(position).getQuestion();
        String a = quesList.get(position).getOptionA();
        String b =quesList.get(position).getOptionB();
        String c = quesList.get(position).getOptionC();
        String d = quesList.get(position).getOptionD();
        int selected = quesList.get(position).getSelectedAns();
        int ans = quesList.get(position).getcorrectAns();

        holder.setData(position , ques, a, b, c, d, selected, ans);

    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView quesNo, question , optionA, optionB, optionC, optionD, result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quesNo=itemView.findViewById(R.id.quesNo);
            question=itemView.findViewById(R.id.question);
            optionA=itemView.findViewById(R.id.optionA);
            optionB=itemView.findViewById(R.id.optionB);
            optionC=itemView.findViewById(R.id.optionC);
            optionD=itemView.findViewById(R.id.optionD);
            result=itemView.findViewById(R.id.result);

        }

        private void setData(int pos, String ques , String a, String b, String c, String d, int selected , int correctAns )
        {
            quesNo.setText("Question No, " + String.valueOf(pos+1));
            question.setText(ques);
            optionA.setText("A :" + a);
            optionB.setText("B :" + b);
            optionC.setText("C :" + c);
            optionD.setText("D :" + d);

            if (selected == -1)
            {
                result.setText("UN-ANSWERED");
                result.setTextColor(itemView.getContext().getResources().getColor(R.color.gray));
                setOptionColor(selected,R.color.black);
                Log.d("itsapp","un answer: ");
            }
            else
            {
                if (selected == correctAns)
                {
                    result.setText("Correct");
                    result.setTextColor(itemView.getContext().getResources().getColor(R.color.green));
                    setOptionColor(selected, R.color.green);
                    Log.d("itsapp","correct: ");
                }
                else
                {
                    result.setText("Wrong");
                    result.setTextColor(itemView.getContext().getResources().getColor(R.color.red));
                    setOptionColor(selected, R.color.red);
                    Log.d("itsapp","wrong: ");
                }
            }
        }

        private void setOptionColor(int selected , int color)
        {
            switch (selected)
            {
                case 1 :
                    optionA.setTextColor(itemView.getContext().getResources().getColor(color));
                    break;
                case 2 :
                    optionB.setTextColor(itemView.getContext().getResources().getColor(color));
                    break;
                case 3 :
                    optionC.setTextColor(itemView.getContext().getResources().getColor(color));
                    break;
                case 4 :
                    optionD.setTextColor(itemView.getContext().getResources().getColor(color));
                    break;

                default:
            }
        }


    }
}
