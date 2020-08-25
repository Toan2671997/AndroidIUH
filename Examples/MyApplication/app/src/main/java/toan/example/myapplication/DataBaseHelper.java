package toan.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String PEOPLE_TABLE = "PEOPLE_TABLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_GROUP_NAME = "GROUP_NAME";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "danhba.db", null, 1);
    }

    // this is called the first time a db accessed. Should code in here to create new db.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + PEOPLE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_GROUP_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    // this is called if the db version number changes. Prevent previous user app from breking when you change db design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPeople(PeopleModel peopleModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, peopleModel.getName());
        contentValues.put(COLUMN_PHONE, peopleModel.getPhone());
        contentValues.put(COLUMN_EMAIL, peopleModel.getEmail());
        contentValues.put(COLUMN_GROUP_NAME, peopleModel.getGroup());

        // check if success or not
        long insert = sqLiteDatabase.insert(PEOPLE_TABLE, null, contentValues);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<PeopleModel> getAll() {
        List<PeopleModel> list = new ArrayList<>();
        String query = " SELECT * FROM " + PEOPLE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        // loop in cursor and return to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                String group = cursor.getString(4);
//                String isDelete = cursor.getInt()

                PeopleModel peopleModel = new PeopleModel(id, name, phone, email, group, false);
                list.add(peopleModel);

            } while (cursor.moveToNext());

        } else {

        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean deletePeople(PeopleModel peopleModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + PEOPLE_TABLE + " WHERE " + COLUMN_ID + " = " + peopleModel.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }


    }
}
