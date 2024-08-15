package com.swift.delivery.app;


public class FastfoodClass {

    private String itemName;
    private String itemPrice;
    private int itemImage;
    public String itemQuantity;

    public FastfoodClass(String iName, String iPrice, int iImage, String iQuantity) {
        this.itemName = iName;
        this.itemPrice = iPrice;
        this.itemImage = iImage;
        this.itemQuantity = iQuantity;
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
