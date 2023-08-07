package com.example.aa.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CarInfoSQLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "CarInfoSpots";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "CarInfoSpot";
    private static final String COL_id = "id";
    private static final String COL_Carname = "carname";
    private static final String COL_Brand = "brand";
    private static final String COL_Model = "model";
    private static final String COL_FuelConsumption = "fuelconsumption";
    private static final String COL_EngineDisplacement = "enginedisplacement";
    private static final String COL_Price = "price";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_Carname + " TEXT, " +
                    COL_Brand + " TEXT, " +
                    COL_Model + " TEXT, " +
                    COL_FuelConsumption + " TEXT, " +
                    COL_EngineDisplacement + " TEXT, " +
                    COL_Price + " TEXT ); ";

    public CarInfoSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<CarInfoSpot> getAllSpots() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_Carname, COL_Brand, COL_Model, COL_FuelConsumption, COL_EngineDisplacement, COL_Price
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);
        List<CarInfoSpot> spotList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String carname = cursor.getString(1);
            String brand = cursor.getString(2);
            String model = cursor.getString(3);
            String fuelconsumption = cursor.getString(4);
            String enginedisplacement = cursor.getString(5);
            String price = cursor.getString(6);
            CarInfoSpot spot = new CarInfoSpot(id, carname, brand, model, fuelconsumption, enginedisplacement,price);
            spotList.add(spot);
        }
        cursor.close();
        return spotList;
    }

    public long insert(CarInfoSpot carInfoSpot) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_Carname, carInfoSpot.getCarname());
        values.put(COL_Brand, carInfoSpot.getBrand());
        values.put(COL_Model, carInfoSpot.getModel());
        values.put(COL_FuelConsumption, carInfoSpot.getFuelconsumption());
        values.put(COL_EngineDisplacement, carInfoSpot.getEnginedisplacement());
        values.put(COL_Price, carInfoSpot.getPrice());
        return db.insert(TABLE_NAME, null, values);
    }

    public int deleteById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}