package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.notepad.data.DBManager;
import com.example.notepad.data.Note;

import java.time.LocalDateTime;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBManager dbManager = new DBManager(this);

        dbManager.createTab();

        dbManager.saveNote(new Note(0,"test","text", LocalDateTime.now()));
        dbManager.findAll();

       // dbManager.dropTab();
    }
}