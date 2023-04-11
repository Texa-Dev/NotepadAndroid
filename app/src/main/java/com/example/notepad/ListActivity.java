package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.notepad.adapters.NoteCursorAdapter;
import com.example.notepad.data.DBManager;
import com.example.notepad.data.Note;
import com.example.notepad.databinding.ActivityListBinding;

import java.time.LocalDateTime;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView notesView = binding.notesRec;
        DBManager manager = new DBManager(this);
        manager.createTab();

        List<Note> list = List.of(
                new Note(1, "h1", "t1", LocalDateTime.now()),
                new Note(2, "h2", "t2", LocalDateTime.now()),
                new Note(3, "h3", "t3", LocalDateTime.now())
        );
        for (Note note : list) {
            manager.saveNote(note);
        }


       // manager.saveNote(new Note(0, "test", "text", LocalDateTime.now()));

        Cursor allCursor = manager.findAllCursor();
        NoteCursorAdapter noteCursorAdapter = new NoteCursorAdapter(allCursor);
        notesView.setAdapter(noteCursorAdapter);

        // dbManager.dropTab();
    }
}