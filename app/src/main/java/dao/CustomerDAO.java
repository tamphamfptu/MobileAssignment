package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DbHelper;

import java.util.ArrayList;

import model.Customer;


public class CustomerDAO {
    DbHelper mydb;
    CustomerDAO customerDAO;
    public CustomerDAO(Context context){
        mydb = new DbHelper(context);
    }


    public boolean check(Customer customer){
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from Customer where fullname =? and password =?",
                new String[]{customer.getName(), customer.getPassword()});
        if(cs.getCount() <=0){
            return false;
        }
        return true;
    }
    public boolean update(Customer customer){
        SQLiteDatabase db = mydb.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", customer.getPassword());
        int row = db.update("Customer", values, "username=?",
                new String[]{customer.getName()});
        return row>0;
    }

    public ArrayList<Customer> readALL(){
        SQLiteDatabase db = mydb.getReadableDatabase();
        ArrayList<Customer> data = new ArrayList<>();
        Cursor cs = db.rawQuery("select * from Customer", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            String username = cs.getString(0);
            String pass = cs.getString(1);
            cs.moveToNext();
        }
        cs.close();
        return data;
    }

    public boolean create(Customer customer){
        SQLiteDatabase db = mydb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", customer.getName());
        values.put("password", customer.getPassword());
        long row = db.insert("Customer", null, values);
        return row>0;
    }

    public boolean delete(String user){
        SQLiteDatabase db = mydb.getReadableDatabase();
        ContentValues values = new ContentValues();
        long row = db.delete("Customer", "username = ?", new String[]{user});
        return row>0;
    }
}
