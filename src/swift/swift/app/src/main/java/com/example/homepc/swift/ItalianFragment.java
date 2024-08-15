package com.swift.delivery.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ItalianFragment extends Fragment {

    public ItalianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_italian, container, false);

        ArrayList<ItalianClass> iFood = new ArrayList<>();
        iFood.add(new ItalianClass("Pasta", "$5.00", R.drawable.pastaone, "0"));
        iFood.add(new ItalianClass("Lasagna", "$8.00", R.drawable.lasagna, "0"));
        iFood.add(new ItalianClass("Italian Pizza", "$16.00", R.drawable.pizza, "0"));
        iFood.add(new ItalianClass("Focaccia Bread", "$5.00", R.drawable.italiaone, "0"));

        ItalianAdapter adapter = new ItalianAdapter(getActivity(), iFood);

        ListView listView = (ListView) view.findViewById(R.id.listview_italian);
        listView.setAdapter(adapter);

        return view;
    }
}
