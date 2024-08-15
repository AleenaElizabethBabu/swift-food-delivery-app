package com.swift.delivery.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class FastFoodFragment extends Fragment {

    public FastFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fast_food, container, false);

        ArrayList<FastfoodClass> fastfood = new ArrayList<>();
        fastfood.add(new FastfoodClass("Beef Burger", "$3.00", R.drawable.beef, "0"));
        fastfood.add(new FastfoodClass("Chicken Burger", "$2.70", R.drawable.chicken, "0"));
        fastfood.add(new FastfoodClass("Zinger Burger", "$4.70", R.drawable.zinger, "0"));
        fastfood.add(new FastfoodClass("Fries", "$1.50", R.drawable.fries, "0"));
        fastfood.add(new FastfoodClass("Zinger Roll", "$2.60", R.drawable.roll, "0"));
        fastfood.add(new FastfoodClass("Club Sandwich", "$3.00", R.drawable.club, "0"));
        fastfood.add(new FastfoodClass("Chicken Wings", "$2.00", R.drawable.wings, "0"));
        fastfood.add(new FastfoodClass("Chicken Broast", "$7.00", R.drawable.broast, "0"));
        fastfood.add(new FastfoodClass("Chicken Nuggets", "$2.60", R.drawable.nuggets, "0"));

        FastFoodAdapter fastfoodadapter = new FastFoodAdapter(getActivity(), fastfood);

        ListView listView = (ListView) view.findViewById(R.id.listview_fastfood);
        listView.setAdapter(fastfoodadapter);

        return view;
    }
}
