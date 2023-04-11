package com.example.notepad.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes";
    public static final String TAB = "notes";
    public static final String ID = "_id";
    public static final String HEADER = "header";
    public static final String TEXT = "text";
    public static final String TIME_CREATE = "timeCreate";

    private SQLiteDatabase db;

    public DBManager(@Nullable Context context) {
        super(context, DB_NAME, null, 1);

        //context(Activity), name(DB name), factory(db cursor (jdbc), version(db version))
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createTab() {
        db = getWritableDatabase();
        String sql = "create table if not exists %s(" +
                " %s int auto increment primary key," +
                " %s text default ''," +
                " %s text default ''," +
                "%s text)";

        sql = String.format(sql, TAB, ID, HEADER, TEXT, TIME_CREATE);

        db.execSQL(sql);
    }

    public void dropTab() {
        db = getWritableDatabase();
        String sql = "drop table if exists " + TAB;
        sql = String.format(sql);
        db.execSQL(sql);
    }

    public void saveNote(Note note){
        db = getWritableDatabase();
        String sql = "insert into %s(%s,%s,%s) VALUES ('%s','%s','%s')";
        sql = String.format(sql, TAB, HEADER, TEXT,TIME_CREATE,
                note.getHeader(),note.getText(),note.getTimeCreate());
        db.execSQL(sql);
    }

    public List<Note> findAll(){
        db = getReadableDatabase();
        String sql = "select * from "+TAB;
        Cursor cursor = db.rawQuery(sql, new String[]{});
        List<Note> noteList = new ArrayList<>();

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID));
            @SuppressLint("Range") String header = cursor.getString(cursor.getColumnIndex(HEADER));
            @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex(TEXT));
            @SuppressLint("Range") LocalDateTime timeCreate = LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(TIME_CREATE)));

            Note note = new Note(id, header, text, timeCreate);
            noteList.add(note);
        }

        cursor.close();
        return noteList;
    }

    public Cursor findAllCursor(){
        db = getReadableDatabase();
        String sql = "select * from "+TAB;
        return db.rawQuery(sql, new String[]{});
    }
}
