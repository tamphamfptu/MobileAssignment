package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DbHelper;

import java.util.ArrayList;

import model.Product;

public class ProductDAO {

    public static ArrayList<Product> getAll(Context context){

        ArrayList<Product> productList = new ArrayList<>();
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from Product", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            int ma = cs.getInt(0);
            String ten = cs.getString(1);
            String hang = cs.getString(2);
            String mausac = cs.getString(3);
            Float price = cs.getFloat(4);
            Product product = new Product(ma, ten,  hang, mausac, price);
            productList.add(product);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return productList;
    }

    public static boolean insert(Context context, String name, String brand, String color, Float price){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ProductName", name);
        values.put("Brand", brand);
        values.put("Color", color);
        values.put("Price", price);
        long row = db.insert("Product", null, values);
        return (row>0);

    }
    public static boolean update (Context context, Product pd){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ProductName", pd.getProductName());
        values.put("Brand", pd.getBrand());
        values.put("Color", pd.getColor());
        values.put("Price", pd.getPrice());
        int row = db.update("Product", values, "Id=?", new String[]{pd.getId() + ""});
        return (row>0);
    }

    public static boolean delete(Context context, int productId){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("Product", "Id=?", new String[]{productId + ""});
        return (row>0);
    }
}
