package com.swift.delivery.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodFragment extends Fragment {
    DatabaseHelper mydb;

    public FoodFragment() {
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        this.mydb = new DatabaseHelper(getContext());

        ArrayList<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Fried Rice", "$6.00", R.drawable.friedrice, "0"));
        foodItems.add(new FoodItem("Sushi", "$8.00", R.drawable.sushi, "0"));
        foodItems.add(new FoodItem("Haka Noodles", "$7.00", R.drawable.haka, "0"));
        foodItems.add(new FoodItem("Corn Soup", "$4.00", R.drawable.soup, "0"));

        FoodAdapter adapter = new FoodAdapter(getActivity(), foodItems);

        ListView listView = view.findViewById(R.id.listview_food);
        listView.setAdapter(adapter);

        return view;
    }
}
