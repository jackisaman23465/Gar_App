package com.example.aa.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSQLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "UserSpots";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "UserSpot";
    private static final String COL_id = "id";
    private static final String COL_username = "email";
    private static final String COL_password = "password";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_username + " TEXT, " +
                    COL_password + " TEXT ); ";

    public UserSQLite(Context context) {
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

    public int findByUsername(String username) {
        SQLiteDatabase db = getWritableDatabase();
        int result=0;
        Cursor cursor=db.query(TABLE_NAME, null, COL_username + "=?",
                new String[] {username}, null, null, null);
        if(cursor!=null){
            result=cursor.getCount();
            cursor.close();
        }
        return result;
    }

    public int findByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        int result=0;
        Cursor cursor=db.query(TABLE_NAME, null, COL_username + "=?" + " and " + COL_password + "=?",
                new String[] {username, password }, null, null, null);
        if(cursor!=null){
            result=cursor.getCount();
            cursor.close();
        }
        return result;
    }

    public long insert(UserSpot userSpot) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_username, userSpot.getUsername());
        values.put(COL_password, userSpot.getPassword());
        return db.insert(TABLE_NAME, null, values);
    }
}
