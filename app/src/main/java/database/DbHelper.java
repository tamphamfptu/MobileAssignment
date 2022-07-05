package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context){

        //Create Database
        super(context, "TestDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table Product(Id integer primary key autoincrement," +
                "ProductName text, Brand text, Color text, Price float) ";
        db.execSQL(sql);

        sql = "Insert Into Product Values(null, 'Super Car', 'Bugatti', 'Red', 30000.999)";
        db.execSQL(sql);
        sql = "Insert Into Product Values(null, 'Sport Car', 'Lambo', 'Rose', 7000.222)";
        db.execSQL(sql);
        sql = "Insert Into Product Values(null, 'Vip Car', 'Bentley', 'Black', 20000.788)";
        db.execSQL(sql);
        sql = "Insert Into Product Values(null, 'Speed Car', 'Audi', 'Blue', 10000.344)";
        db.execSQL(sql);

        //Customer
        //Create table
        sql = "CREATE TABLE Customer(fullname not null, password )";
        db.execSQL(sql);
        //Insert data to created table
        db.execSQL("INSERT INTO Customer values('tampvn', '123456')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Product");
        onCreate(db);
    }
}
