package com.ritech.quizkarlo.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.Models.CategoryModel;
import com.ritech.quizkarlo.R;
import com.ritech.quizkarlo.TestActivity;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryModel> cat_list;

    public CategoryAdapter(List<CategoryModel> cat_list) {
        this.cat_list = cat_list;
    }


    @Override
    public int getCount() {
        return cat_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View myView;

        if (view == null) {
            myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_item_layout, viewGroup, false);
        } else {
            myView = view;
        }

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // for category selection
                DbQuery.g_selected_cat_index = i;

                Intent intent = new Intent(myView.getContext(), TestActivity.class);

                myView.getContext().startActivity(intent);

            }
        });

        TextView catName = myView.findViewById(R.id.catName);
        TextView noOfTests = myView.findViewById(R.id.no_of_tests);

        catName.setText(cat_list.get(i).getName());
        noOfTests.setText(String.valueOf(cat_list.get(i).getNoOfTests()) + " Tests");

        return myView;
    }
}
