package com.example.organizeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StoreExamDates extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exam_dates";
    private static final String TABLE_NAME = "exam_dates_store_table";

    StoreExamDates(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table "+ TABLE_NAME + "(id INTEGER PRIMARY KEY, exam TEXT , date TEXT , time TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean AddExam(String exam , String date , String time){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("exam",exam);
        cv.put("date",date);
        cv.put("time",time);
        sqLiteDatabase.insert(TABLE_NAME,null,cv);
        return true;
    }

    @SuppressLint("Range")
    public ArrayList getAllExams(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Exam> arrayList = new ArrayList<Exam>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Exam e;
            String name = cursor.getString(cursor.getColumnIndex("exam"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            e = new Exam(name , date , time);
            arrayList.add(e);
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public boolean del(String name) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME+" where exam = ?",new String[]{name});
        if(cursor.getCount() > 0) {
            long res = sqLiteDatabase.delete(TABLE_NAME, "exam=?", new String[]{name});
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
