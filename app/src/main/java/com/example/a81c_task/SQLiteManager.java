package com.example.a81c_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;

import java.net.URL;


public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "YOUTUBE_PLAYLIST";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "USER";
    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String PASSWORD_FIELD = "password";
    private static final String URL_FIELD = "url";


    public SQLiteManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context)
    {
        if (sqLiteManager == null)
        {
            sqLiteManager = new SQLiteManager(context);
        }

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(ID_FIELD)
                .append(" INT NOT NULL PRIMARY KEY, ")
                .append(NAME_FIELD)
                .append(" TEXT, ")
                .append(PASSWORD_FIELD)
                .append(" TEXT, ")
                .append(URL_FIELD)
                .append(" TEXT)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addItemToDatabase(UserData user)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, user.getUserID());
        contentValues.put(NAME_FIELD, user.getUserName());
        contentValues.put(PASSWORD_FIELD, user.getUserPassword());
        contentValues.put(URL_FIELD, user.getUserLink());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateItemListArray()
    {
        UserData.itemList.clear();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if (result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    String password = result.getString(2);
                    String url = result.getString(3);
                    UserData item = new UserData(name, password, url, id);
                    if (!UserData.itemList.contains(item))
                    {
                        UserData.itemList.add(item);
                    }
                }
            }
        }

    }

    public int deleteItem(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?", new String [] {id});
    }

}
