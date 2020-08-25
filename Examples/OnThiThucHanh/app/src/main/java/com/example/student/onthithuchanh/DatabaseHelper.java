package com.example.student.onthithuchanh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "QLNhanVien.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE NHANVIENS(id integer primary key, ten text,cmnd int, diachi text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NHANVIENS");
    }
    public void insertNhanVien(NhanVien nv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", null);
        contentValues.put("ten", nv.getTen());
        contentValues.put("cmnd", nv.getCmnd());
        contentValues.put("diachi", nv.getDiachi());
        db.insert("NHANVIENS", null, contentValues);
        db.close();
    }
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from NHANVIENS", null);
        if(cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false) {
            NhanVien authorModel = new NhanVien(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
            list.add(authorModel);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public NhanVien getNhanVienWithId(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from NHANVIENS where id=" + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        NhanVien authorModel = new NhanVien(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));

        cursor.close();
        db.close();
        return authorModel;
    }
    public void deleteNhanVien(int idNHANVIENS){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("NHANVIENS", "id=?", new String[]{String.valueOf(idNHANVIENS)});
    }
    public void updateBook(NhanVien nv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id", nv.getId());
        values.put("ten", nv.getTen());
        values.put("cmnd", nv.getCmnd());
        values.put("diachi", nv.getDiachi());
        db.update("NHANVIENS", values,"id=?", new String[]{String.valueOf(nv.getId())});
        db.close();
    }

}
