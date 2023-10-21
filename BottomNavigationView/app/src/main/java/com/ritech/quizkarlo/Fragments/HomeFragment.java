package com.ritech.quizkarlo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.ritech.quizkarlo.Adapters.CategoryAdapter;
import com.ritech.quizkarlo.DbQuery;
import com.ritech.quizkarlo.MainActivity;
import com.ritech.quizkarlo.R;

public class HomeFragment extends Fragment {

    private GridView catView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Categories");

        catView = view.findViewById(R.id.cat_Grid);

        CategoryAdapter adapter = new CategoryAdapter(DbQuery.g_catList);
        catView.setAdapter(adapter);

        return view;
    }




}
