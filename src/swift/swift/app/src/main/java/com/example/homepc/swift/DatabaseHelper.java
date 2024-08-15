package com.swift.delivery.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SWIFTData";

    
    private static final String TABLE_CUSTOMERS = "Customers";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PASSWORD = "Password";

  
    private static final String TABLE_ORDERS = "OrderDetails";
    private static final String COLUMN_ORDER_NO = "OrderNo";
    private static final String COLUMN_ITEM_NAME = "ItemName";
    private static final String COLUMN_ITEM_QUANTITY = "ItemQuantity";
    private static final String COLUMN_ITEM_PRICE = "ItemPrice";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ORDER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_QUANTITY + " TEXT, " +
                COLUMN_ITEM_PRICE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public boolean addAccount(String name, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_CUSTOMERS, null, contentValues);
        return result != -1;
    }

    public Cursor getAccounts() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS, null);
    }

    public Cursor checkAccount(String searchStr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " +
                COLUMN_NAME + " LIKE '%" + searchStr + "%'", null);
    }

    
    public boolean addToCart(String itemName, String quantity, String price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_NAME, itemName);
        contentValues.put(COLUMN_ITEM_QUANTITY, quantity);
        contentValues.put(COLUMN_ITEM_PRICE, price);
        long result = db.insert(TABLE_ORDERS, null, contentValues);
        return result != -1;
    }

    public Cursor getOrderDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDERS, null);
    }

    public Integer deleteOrder(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ORDERS, COLUMN_ORDER_NO + " = ?", new String[]{id});
    }

    public void deleteAllOrders() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ORDERS);
    }
}
