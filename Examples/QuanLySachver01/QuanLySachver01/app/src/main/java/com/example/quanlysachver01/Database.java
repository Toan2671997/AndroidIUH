package com.example.quanlysachver01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // truy vấn dữ liệu không trả kết quả,: CRUD,
    public void queryDate(String sql){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

    }
    public void addStudent(sach s){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hinh",s.getHinh());
        values.put("tensach",s.getTen());
        values.put("tacgia",s.getTacgia());
        values.put("loaisach",s.getLoaisach());
        values.put("ngayphathanh",s.getNgayphathanh());
        db.insert("sach",null,values);
        db.close();
    }
    public void updateStudent(sach s){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hinh",s.getHinh());
        values.put("tensach",s.getTen());
        values.put("tacgia",s.getTacgia());
        values.put("loaisach",s.getLoaisach());
        values.put("ngayphathanh",s.getNgayphathanh());
        db.update("sach",values, "Id=?",new String[]{String.valueOf(s.getId())} );
        db.close();
    }
    // Truye vấn trả về kết quả: Select,
    public Cursor getdata(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }


}
