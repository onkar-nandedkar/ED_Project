package com.example.organizeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseStore extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "edbase";
    private static final String TABLE_NAME = "TodoTable";

    DatabaseStore(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table "+ TABLE_NAME + "(id INTEGER PRIMARY KEY, task TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean AddTask(String task){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task",task);
        sqLiteDatabase.insert(TABLE_NAME,null,cv);
        return true;
    }

    @SuppressLint("Range")
    public ArrayList getAllTasks(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("task")));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public boolean del(String task) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME+" where task = ?",new String[]{task});
        if(cursor.getCount() > 0) {
            long res = sqLiteDatabase.delete(TABLE_NAME, "task=?", new String[]{task});
            if (res == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }
}
