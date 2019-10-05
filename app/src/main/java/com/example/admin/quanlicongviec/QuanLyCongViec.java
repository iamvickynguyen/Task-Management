package com.example.admin.quanlicongviec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 5/17/2018.
 */

public class QuanLyCongViec extends SQLiteOpenHelper {
    public QuanLyCongViec(Context context) { super(context, "quanlycongviec", null, 1);}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /*create table qlcv
                (
                        _id integer primary key autoincrement,
                        noidung text,
                        thoigian text,
                        note text
                )*/

        String sql="create table qlcv " +
                "( " +
                "_id integer primary key autoincrement, " +
                "noidung text, " +
                "thoigian text, " +
                "note text" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop database if exists qlcv";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void themCongViec (CongViec cv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noidung", cv.noidung);
        values.put("thoigian", cv.thoigian);
        values.put("note", cv.note);
        db.insert("qlcv", null, values);
    }

    public void xoaCongViec (int _id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("qlcv", "_id=?", new String[]{_id+""});
    }

    public void suaCongViec(CongViec cv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noidung", cv.noidung);
        values.put("thoigian", cv.thoigian);
        values.put("note", cv.note);
        db.update("qlcv", values,"_id=?", new String[]{cv._id+""});
    }

    public ArrayList<CongViec> layTatCaCongViec()
    {
        ArrayList<CongViec> ds = new ArrayList<CongViec>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from qlcv", null);
        if (c.moveToFirst())
        {
            do{
                int _id = c.getInt(0);
                String noidung = c.getString(1);
                String thoigian = c.getString(2);
                String note = c.getString(3);
                CongViec cv = new CongViec(_id, noidung, thoigian, note);
                ds.add(cv);
            }while (c.moveToNext());
        }
        return ds;
    }
}
