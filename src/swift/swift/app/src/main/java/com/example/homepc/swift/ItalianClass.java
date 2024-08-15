package com.swift.delivery.app;


public class ItalianClass {
    private String itemName;
    private String itemPrice;
    private int itemImage;
    public String itemQuantity;

    public ItalianClass(String iName, String iPrice, int iImage, String iQuantity) {
        itemName = iName;
        itemPrice = iPrice;
        itemImage = iImage;
        itemQuantity = iQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getImageResourceId() {
        return itemImage;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }
}
