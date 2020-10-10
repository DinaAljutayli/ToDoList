package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.todolist.GroceryContract.*;
import com.example.todolist.ui.main.Fragment2;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="App.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String TABLE_NAME2 ="grocerylist";

    public static final String COL_1 ="Username";
    public static final String COL_2 ="Email";
    public static final String COL_3 ="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                    GroceryEntry.TABLE_NAME2 + " (" +
                    GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    GroceryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                    GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";
            sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);


            sqLiteDatabase.execSQL("CREATE TABLE registeruser (Username TEXT PRIMARY  KEY , Email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroceryEntry.TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password, String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",user);
        contentValues.put("Email",Email);
        contentValues.put("password",password);

        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password ){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection =   COL_1 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }
}


