package com.example.homepc.restauranteatitapp;

public class ItalianClass {
    private String Itemname;
    private String Itemprice;
    private int Itemimage;
    public String Itemquantity;

    public ItalianClass(String iName, String iPrice, int iImage, String iquantity) {
        Itemname = iName;
        Itemprice = iPrice;
        Itemimage = iImage;
        Itemquantity = iquantity;
    }

    public String getItemName() {
        return Itemname;
    }

    public String getItemPrice() { return Itemprice; }

    public int getImageResourceId() { return Itemimage; }

    public String getItemquantity() { return Itemquantity; }
}
