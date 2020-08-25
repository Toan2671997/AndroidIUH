package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String OBJECT_TABLE = "OBJECT_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_A = "A";
    public static final String COLUMN_B = "B";
    public static final String COLUMN_C = "C";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "myData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + OBJECT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_A + " TEXT, " + COLUMN_B + " TEXT, " + COLUMN_C + " TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<ObjectModel> getAll() {
        List<ObjectModel> list = new ArrayList<>();
        String query = " SELECT * FROM " + OBJECT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        // loop in cursor and return to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String a = cursor.getString(1);
                String b = cursor.getString(2);
                String c = cursor.getString(3);

                ObjectModel objectModel = new ObjectModel(id, a, b, c);
                list.add(objectModel);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return list;
    }

    public ObjectModel getById(ObjectModel objectModel) {

        String query = "SELECT * FROM " + OBJECT_TABLE + " WHERE " + COLUMN_ID + " = " + objectModel.getId();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null)
            cursor.moveToFirst();
        ObjectModel objectModelGet = new ObjectModel(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        );
        cursor.close();
        db.close();
        return objectModelGet;
    }

    public boolean addObj(ObjectModel objectModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_A, objectModel.getA());
        contentValues.put(COLUMN_B, objectModel.getB());
        contentValues.put(COLUMN_C, objectModel.getC());

        // check if success or not
        long insert = sqLiteDatabase.insert(OBJECT_TABLE, null, contentValues);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateObj(ObjectModel objectModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_A, objectModel.getA());
        contentValues.put(COLUMN_B, objectModel.getB());
        contentValues.put(COLUMN_C, objectModel.getC());
        // sqLiteDatabase.update(OBJECT_TABLE, contentValues, "ID=?",new String[]{String.valueOf(objectModel.getId())} );
        long update = sqLiteDatabase.update(OBJECT_TABLE, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(objectModel.getId())});
        if(update == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteObj(ObjectModel objectModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + OBJECT_TABLE + " WHERE " + COLUMN_ID + " = " + objectModel.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
