package com.example.lighthouse.transportationdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Lighthouse on 2017/5/27.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TASK = "create table tasking("
            + "id text primary key autoincrement,"
            +"time text,"
            +"tasknumber,text"
            + "taskstart text,"
            +"taskend text,"
            +"taskmaster text)";
    public static final String CREATE_FINISH = "create table taskfinished("
            + "id text primary key autoincrement,"
            +"time text,"
            +"tasknumber,text"
            + "taskstart text,"
            +"taskend text,"
            +"taskmaster text)";
    public static final String CREATE_CANCEl = "create table taskcanceled("
            + "id text primary key autoincrement,"
            +"time text,"
            +"tasknumber,text"
            + "taskstart text,"
            +"taskend text,"
            +"taskmaster text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TASK);
        db.execSQL(CREATE_FINISH);
        db.execSQL(CREATE_CANCEl);
        Toast.makeText(mContext,"Create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists tasking");
        onCreate(db);
    }
}
