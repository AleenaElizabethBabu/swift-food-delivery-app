package com.swift.delivery.app;


public class FoodItem {

    private String itemName;
    private String itemPrice;
    private int imageResourceId;
    public String itemQuantity;

    public FoodItem(String name, String price, int image, String quantity) {
        itemName = name;
        itemPrice = price;
        imageResourceId = image;
        itemQuantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }
}
