package com.example.bracesteeth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "braces.db";
    public static final String TABLE_NAME = "bracesTab";
    public static final String COL1 = "ID";
    public static final String COL2 = "DATE";
    public static final String COL3 = "PRIX";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " DATE TEXT, PRIX REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String _DATE, String _PRIX) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, _DATE);
        contentValues.put(COL3, _PRIX);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String countPrix(){
        String res="0";
        String sql = "SELECT SUM(PRIX) FROM " + TABLE_NAME;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            res=cursor.getString(0);
        }
        cursor.close();
        return res;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void delete(String id) {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+ COL1 +"="+id+";");
    }
}
