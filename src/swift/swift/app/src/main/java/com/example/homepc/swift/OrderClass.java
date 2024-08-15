package com.swift.delivery.app;


public class OrderClass {
    private String itemId;
    private String itemName;
    private String itemQuantity;
    private String itemPrice;

    public OrderClass(String id, String name, String quantity, String price) {
        itemId = id;
        itemName = name;
        itemQuantity = quantity;
        itemPrice = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice; // Ensure this value is formatted in dollars if necessary
    }

    public String getItemQuantity() {
        return itemQuantity;
    }
}
