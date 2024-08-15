package com.swift.delivery.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FoodAdapter extends ArrayAdapter<FoodItem> {
    int quantity = 0;
    int i = 0;
    int value = 0;
    String hold = "";
    int pos = 0, counter = 1;
    String[] orderDetails = new String[1000];
    DatabaseHelper mydb;
    String Number, Name, Quantity, Price = "";

    public FoodAdapter(Activity context, ArrayList<FoodItem> foodItems) {
        super(context, 0, foodItems);
        this.mydb = new DatabaseHelper(context.getApplicationContext());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_item_layout, parent, false);
        }

        FoodItem currentFoodItem = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.item_image);
        imageView.setImageResource(currentFoodItem.getImageResourceId());

        TextView nameTextView = listItemView.findViewById(R.id.item_name);
        nameTextView.setText(currentFoodItem.getItemName());

        TextView priceTextView = listItemView.findViewById(R.id.item_price);
        priceTextView.setText("Price $" + currentFoodItem.getItemPrice());

        Button plusButton = listItemView.findViewById(R.id.plus_btn);
        plusButton.setOnClickListener(view -> quantity++);

        Button minusButton = listItemView.findViewById(R.id.minus_btn);
        minusButton.setOnClickListener(view -> {
            if (quantity > 0) quantity--;
        });

        hold = currentFoodItem.getItemQuantity();
        value = Integer.parseInt(hold);
        quantity = value + quantity;

        TextView quantityTextView = listItemView.findViewById(R.id.quantity);
        quantityTextView.setText(String.valueOf(quantity));

        Button cartButton = listItemView.findViewById(R.id.cart_btn);
        cartButton.setTag(position);

        cartButton.setOnClickListener(view -> {
            pos = (Integer) view.getTag();
            if (quantity != 0) {
                boolean isInserted = false;
                switch (pos) {
                    case 0:
                        isInserted = mydb.addToCart("Fried Rice", String.valueOf(quantity), String.valueOf(6.00 * quantity));
                        break;
                    case 1:
                        isInserted = mydb.addToCart("Sushi", String.valueOf(quantity), String.valueOf(8.00 * quantity));
                        break;
                    case 2:
                        isInserted = mydb.addToCart("Haka Noodles", String.valueOf(quantity), String.valueOf(7.00 * quantity));
                        break;
                    case 3:
                        isInserted = mydb.addToCart("Corn Soup", String.valueOf(quantity), String.valueOf(4.00 * quantity));
                        break;
                }

                if (isInserted) {
                    orderDetails[i] = "Id " + counter + " " + currentFoodItem.getItemName() + " Price $ " + currentFoodItem.getItemPrice() * quantity + " ";
                    counter++;
                    i++;
                    quantity = 0;
                    Toast.makeText(getContext(), "Order Added Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Quantity value can't be zero or lesser!!!", Toast.LENGTH_SHORT).show();
            }
        });

        return listItemView;
    }
}
