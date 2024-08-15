package com.swift.delivery.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class ItalianAdapter extends ArrayAdapter<ItalianClass> {
    int quantity = 0;
    int i = 0;
    int value = 0;
    String hold = "";
    int pos = 0, counter = 1;
    String[] orderDetails = new String[1000];
    DatabaseHelper mydb;

    public ItalianAdapter(Activity context, ArrayList<ItalianClass> i_food) {
        super(context, 0, i_food);
        this.mydb = new DatabaseHelper(context.getApplicationContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_design, parent, false);
        }

        ItalianClass currenti_food = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.item_image);
        imageView.setImageResource(currenti_food.getImageResourceId());

        TextView nameTextView = listItemView.findViewById(R.id.item_name);
        nameTextView.setText(currenti_food.getItemName());

        TextView priceTextView = listItemView.findViewById(R.id.item_price);
        priceTextView.setText("Price $" + currenti_food.getItemPrice());

        Button plus = listItemView.findViewById(R.id.plus_btn);
        plus.setOnClickListener(view -> quantity += 1);

        Button minus = listItemView.findViewById(R.id.minus_btn);
        minus.setOnClickListener(view -> {
            if (quantity > 0) quantity -= 1;
        });

        hold = currenti_food.getItemQuantity();
        value = Integer.parseInt(hold);
        quantity = value + quantity;

        TextView quantityTextView = listItemView.findViewById(R.id.quantity);
        quantityTextView.setText(String.valueOf(quantity));

        Button cart_btn = listItemView.findViewById(R.id.cart_btn);
        cart_btn.setTag(position);

        cart_btn.setOnClickListener(view -> {
            pos = (Integer) view.getTag();
            if (quantity != 0) {
                boolean isInserted = false;
                String itemName = "";
                int itemPrice = 0;

                switch (pos) {
                    case 0:
                        itemName = "Pasta";
                        itemPrice = 450;
                        break;
                    case 1:
                        itemName = "Lasagna";
                        itemPrice = 650;
                        break;
                    case 2:
                        itemName = "Italian Pizza";
                        itemPrice = 1250;
                        break;
                    case 3:
                        itemName = "Focaccia Bread";
                        itemPrice = 450;
                        break;
                }

                isInserted = mydb.Add_to_Cart(itemName, String.valueOf(quantity), String.valueOf(itemPrice * quantity));

                if (isInserted) {
                    orderDetails[i] = "Id " + counter + " " + itemName + " Price $" + itemPrice * quantity + " ";
                    counter++;
                    i++;
                    quantity = 0;
                    Toast.makeText(getContext(), "Order Added Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Quantity value can't be zero or lesser!!!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), String.join("\n", orderDetails), Toast.LENGTH_LONG).show();
            }
        });

        return listItemView;
    }
}
