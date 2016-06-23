package com.buaa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/3.
 */
public class DButil extends SQLiteOpenHelper{
    private static final String DBNAME = "interview.db";
    private static final int DBVERSIOIN = 1;
    public DButil(Context ctx){
        super(ctx,DBNAME,null,1);
    }

    public DButil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table ques_ans("+"id integer primary key,"+"question text,"+"answer text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
