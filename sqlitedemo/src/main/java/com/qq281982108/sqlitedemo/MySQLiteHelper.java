package com.qq281982108.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-05 19:34
 * 类名：MySQLiteHelper
 * 修改备注：
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String CREATE_NEWS = "create table news ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "content text, "
            + "publishdate integer,"
            + "commentcount integer)";

    public static final String CREATE_COMMENT = "create table comment ("
            + "id integer primary key autoincrement, "
            + "content text)";

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
        db.execSQL(CREATE_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(CREATE_COMMENT);
            default:
        }
    }

}
