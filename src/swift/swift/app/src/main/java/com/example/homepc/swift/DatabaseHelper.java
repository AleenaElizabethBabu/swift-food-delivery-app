package com.swift.delivery.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SWIFTData";

   
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USER_ID = "Id";
    public static final String COLUMN_USER_NAME = "Name";
    public static final String COLUMN_USER_PASSWORD = "Password";

    
    public static final String TABLE_ORDERS = "Orders";
    public static final String COLUMN_ORDER_NO = "OrderNo";
    public static final String COLUMN_ITEM_NAME = "ItemName";
    public static final String COLUMN_ITEM_QUANTITY = "ItemQuantity";
    public static final String COLUMN_ITEM_PRICE = "ItemPrice";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_ORDERS + " (" +
                COLUMN_ORDER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_QUANTITY + " TEXT, " +
                COLUMN_ITEM_PRICE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public boolean addUser(String name, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, name);
        contentValues.put(COLUMN_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
    }

    public Cursor checkUserAccount(String searchStr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_NAME + " LIKE '%" + searchStr + "%'", null);
    }

    public boolean addOrder(String name, String quantity, String price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_NAME, name);
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
