package com.gaurav.stockmanage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gaurav.stockmanage.model.Productmodel;

import java.util.ArrayList;

public class DBhandler extends SQLiteOpenHelper {

    private static final String dbname ="stock";

    public DBhandler(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String q = "create table product(id integer primary key autoincrement, name text ,quantity text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists product");
        onCreate(db);

    }
    public boolean insert(String name, String quantity){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("quantity",quantity);

        long result = sqLiteDatabase.insert("product",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }


    public ArrayList<Productmodel> read(){
        // here we are creating a database to read our database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // here we are creating a cursor to read the data from database with query
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM product",null);
        ArrayList<Productmodel> arrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                arrayList.add(new Productmodel(cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }

        cursor.close();
//        Log.d("TAG", "read: "+arrayList.toString());
        return arrayList;
    }


//    public Cursor getdata(){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from product",null);
//        return cursor;
//    }

    public boolean delete(String quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from product where quantity =?",new String[]{quantity});

        if(cursor.getCount()>0){
            long result = sqLiteDatabase.delete("product","quantity=?",new String[]{quantity});
            if(result ==-1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }



    }


    public boolean update(String name , String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
//        contentValues.put("name",name);
        contentValues.put("quantity", quantity);
        Cursor cursor = db.rawQuery("select * from product where name=?", new String[]{name});
        long r = db.update("product", contentValues, "name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            if (r == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }


}
