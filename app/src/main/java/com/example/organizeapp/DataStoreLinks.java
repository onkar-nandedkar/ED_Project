package com.example.organizeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataStoreLinks extends SQLiteOpenHelper{


        private static final String DATABASE_NAME = "edbase_links";
        private static final String TABLE_NAME = "links_table";
        private static final String ID = "id";
        private static final String COMMENT = "comment";
        private static final String LINKS = "link";
        private static final String RATING = "rating";
        private static final String TITLE = "title";

        DataStoreLinks(Context context){
        super(context,DATABASE_NAME,null,1);
    }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String createTable = "CREATE TABLE "+ TABLE_NAME + " ("+"id INTEGER PRIMARY KEY, "+ COMMENT +" TEXT, " + LINKS + " TEXT, " + RATING+" TEXT, " + TITLE+" TEXT"+")";
            sqLiteDatabase.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

        public boolean AddLink(String link , String rating , String comments , String title){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(RATING,rating);
            cv.put(LINKS,link);
            cv.put(COMMENT,comments);
            cv.put(TITLE,title);
            sqLiteDatabase.insert(TABLE_NAME,null,cv);
            return true;
        }

        @SuppressLint("Range")
        public ArrayList getAllTitles(){
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ArrayList<String> arrayList = new ArrayList<String>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                arrayList.add(cursor.getString(cursor.getColumnIndex(TITLE)));
                cursor.moveToNext();
            }
            return  arrayList;
        }

        @SuppressLint("Range")
        public String[] getRecord(int id){
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            String[] ans = {};
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                if(id == cursor.getInt(cursor.getColumnIndex(ID))){
                    String title = cursor.getString(cursor.getColumnIndex(TITLE));
                    String rating = cursor.getString(cursor.getColumnIndex(RATING));
                    String comment = cursor.getString(cursor.getColumnIndex(COMMENT));
                    String link = cursor.getString(cursor.getColumnIndex(LINKS));
                    return new String[]{title,rating,comment,link};
                }
                cursor.moveToNext();
            }
            return new String[]{};
        }
        public boolean del_link(String title) {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME+" where title = ?",new String[]{title});
            if(cursor.getCount() > 0) {
                long res = sqLiteDatabase.delete(TABLE_NAME, "title=?", new String[]{title});
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
