package com.example.homepc.restauranteatitapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItalianAdapter extends ArrayAdapter<ItalianClass> {
    int quantity = 0;
    int pos = 0, counter = 1;
    String[] order_details = new String[1000];
    DatabaseHelper mydb;
    String Number, Name, Quantity, Price = "";

    public ItalianAdapter(Activity context, ArrayList<ItalianClass> i_food) {
        super(context, 0, i_food);
        this.mydb = new DatabaseHelper(context.getApplicationContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.menu_design, parent, false);
        }

        ItalianClass currenti_food = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.item_image);
        imageView.setImageResource(currenti_food.getImageResourceId());

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.item_name);
        nameTextView.setText(currenti_food.getItemName());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.item_price);
        priceTextView.setText("Price " + currenti_food.getItemPrice());

        Button plus = (Button) listItemView.findViewById(R.id.plus_btn);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
            }
        });

        Button minus = (Button) listItemView.findViewById(R.id.minus_btn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 0) quantity--;
            }
        });

        String hold = currenti_food.getItemquantity();
        int value = Integer.parseInt(hold);
        quantity = value + quantity;

        TextView quantityTextView = (TextView) listItemView.findViewById(R.id.quantity);
        quantityTextView.setText(String.valueOf(quantity));

        Button cart_btn = (Button) listItemView.findViewById(R.id.cart_btn);
        cart_btn.setTag(position);

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = (Integer) view.getTag();
                if (quantity != 0) {
                    boolean isinserted = false;
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

                    isinserted = mydb.Add_to_Cart(itemName, String.valueOf(quantity), String.valueOf(itemPrice * quantity));

                    if (isinserted) {
                        order_details[counter - 1] = "Id " + counter + " " + itemName + " Price Rs " + itemPrice * quantity + " ";
                        counter++;
                        quantity = 0;
                        Toast.makeText(getContext(), "Order Added Successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Quantity value can't be zero or lesser!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return listItemView;
    }
}
