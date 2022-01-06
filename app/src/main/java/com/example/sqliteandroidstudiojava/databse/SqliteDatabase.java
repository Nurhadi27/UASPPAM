package com.example.sqliteandroidstudiojava.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteandroidstudiojava.FoodList;

import java.util.ArrayList;
import java.util.List;


public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION =	1;
    private static final String DATABASE_NAME = "menuDB";
    private final static String TABLE_NAME ="ANIME";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_SCORE = "SCORE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_URL = "URL";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE	TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME+ " TEXT," + COLUMN_SCORE+ " INTEGER," + COLUMN_DESCRIPTION+ " TEXT," + COLUMN_URL+ " TEXT);";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<FoodList> listAnime(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<FoodList> listFoodList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int score = cursor.getInt(2);
                String desc = cursor.getString(3);
                String url = cursor.getString(4);
                listFoodList.add(new FoodList(id,name,score,desc,url));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listFoodList;
    }

    public FoodList getAnime(int id){
        String sql = "select * from " + TABLE_NAME+" where "+COLUMN_ID+"	= ?";
        SQLiteDatabase db = this.getReadableDatabase();

        FoodList mFoodList = new FoodList();

        Cursor cursor=db.rawQuery(sql, new String[] { String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                mFoodList.setName(cursor.getString(1));
                mFoodList.setScore(cursor.getInt(2));
                mFoodList.setDescription(cursor.getString(3));
                mFoodList.setUrl( cursor.getString(4));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return mFoodList;
    }

    public void newAnime(FoodList mFoodList){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mFoodList.getName());
        values.put(COLUMN_SCORE, mFoodList.getScore());
        values.put(COLUMN_DESCRIPTION, mFoodList.getDescription());
        values.put(COLUMN_URL, mFoodList.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }


}
