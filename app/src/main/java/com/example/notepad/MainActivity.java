package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.notepad.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        binding.editText.setText("111");
        binding.editText.setTextSize(25);

       /* SpannableString string = new SpannableString("0123456789");
        ForegroundColorSpan span = new ForegroundColorSpan(0xFFFF0000);
        string.setSpan(span,3,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.editText.setText(string);*/
        binding.colorBtn.setTag(new ForegroundColorSpan(0x00000000));

        binding.colorBtn.setOnClickListener(view -> {

            SpannableString spannable = new SpannableString(binding.editText.getText());
            spannable.setSpan(binding.colorBtn.getTag(),
                    binding.editText.getSelectionStart(),
                    binding.editText.getSelectionEnd(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        });

       /* binding.colorBtn.setOnLongClickListener(view -> {

        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsMenu:
                Log.d("FF", "onOptionsItemSelected: settingsMenu");
                break;
            case R.id.lightMenu:
                Log.d("FF", "onOptionsItemSelected: lightMenu");
                break;
            case R.id.darkMenu:
                Log.d("FF", "onOptionsItemSelected: darkMenu");
                break;
            case R.id.saveMenu:
                try {
                    FileOutputStream output = openFileOutput("text", MODE_PRIVATE);
                    output.write(binding.editText.getText().toString().getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.loadMenu:
                try {
                    FileInputStream input = openFileInput("text");
                    int c;
                    StringBuilder builder = new StringBuilder();
                    while ((c = input.read()) > -1) {
                        builder.append((char) c);
                    }
                    binding.editText.setText(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}