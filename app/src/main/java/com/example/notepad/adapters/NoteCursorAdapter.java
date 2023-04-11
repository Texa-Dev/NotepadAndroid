package com.example.notepad.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.ListActivity;
import com.example.notepad.NoteActivity;
import com.example.notepad.R;
import com.example.notepad.data.DBManager;

public class NoteCursorAdapter extends RecyclerView.Adapter<NoteCursorAdapter.NoteCursorHolder> {
    private Cursor cursor;
private final String TAG ="FF";
    public NoteCursorAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public NoteCursorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        Log.d(TAG, "first: ");
        return new NoteCursorHolder(view);
    }

    @SuppressLint("Range")
    @Override
    public void onBindViewHolder(@NonNull NoteCursorHolder holder, int position) {

        if (cursor.moveToPosition(position)) {
            Log.d("FF", "onBindViewHolder: SUUUUUKA ");
            holder.headerView.setText(cursor.getString(cursor.getColumnIndex(DBManager.HEADER)));
            holder.textView.setText(cursor.getString(cursor.getColumnIndex(DBManager.TEXT)));
            holder.timeCreatingView.setText(cursor.getString(cursor.getColumnIndex(DBManager.TIME_CREATE)));
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class NoteCursorHolder extends RecyclerView.ViewHolder {

        TextView headerView;
        TextView textView;
        TextView timeCreatingView;

        public NoteCursorHolder(@NonNull View itemView) {
            super(itemView);

            headerView = itemView.findViewById(R.id.headerView);
            textView = itemView.findViewById(R.id.textView);
            timeCreatingView = itemView.findViewById(R.id.dateView);

            itemView.setOnClickListener(view -> {
                /*Snackbar make = Snackbar.make(view, headerView.getText(), BaseTransientBottomBar.LENGTH_INDEFINITE);
                make.setAction("X", view1 -> {
                    make.dismiss();
                });
                make.show();*/
                Context context = itemView.getContext();
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("header", headerView.getText());
                intent.putExtra("text", textView.getText());
                intent.putExtra("timeCreated", timeCreatingView.getText());
                context.startActivity(intent);

                //((Activity)context).finish(); обычно так не делаеться , но так можно
            });
        }
    }
}
