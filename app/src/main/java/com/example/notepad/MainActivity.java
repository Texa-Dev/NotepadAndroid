package com.example.notepad;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.databinding.ActivityMainBinding;
import com.example.notepad.settings.Settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Settings.settings().load(this);
        int fontSize = Settings.settings().loadFontSize(this).getFontSize();
        if (fontSize > 0) {
            binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
        int foregroundColor = Settings.settings().loadForegroundColorSpan(this).getForegroundColorSpan();
        if (foregroundColor != 0) {
            binding.editText.setTextColor(foregroundColor);
        }
        int backgroundColor = Settings.settings().loadBackgroundColorSpan(this).getBackgroundColor();
        if (backgroundColor != 0) {
            binding.editText.setBackgroundColor(backgroundColor);
        }
        int styleSpan = Settings.settings().loadStyleSpan(this).getStyleSpan();
        if (styleSpan != 0) {
            Log.d("FF", "styleSpan.getStyle(): "+ styleSpan);
            binding.editText.setTypeface(Typeface.SERIF, styleSpan);
        }
        //
        // binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,settings.getFontSize());

        // для проверки
        //  binding.editText.setText(String.valueOf(fontSize));

        /*SpannableString string = new SpannableString("0123456789");
        ForegroundColorSpan span = new ForegroundColorSpan(0xFFFF0000);
        string.setSpan(span,3,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.editText.setText(string);*/

        binding.colorBtn.setTag(new ForegroundColorSpan(Color.BLACK));
        binding.colorBtn.setOnClickListener(view -> {
            Object tag = binding.colorBtn.getTag();
            if (tag instanceof ForegroundColorSpan) {

                ForegroundColorSpan foregroundColorSpan = (ForegroundColorSpan) tag;
                SpannableString spannable = new SpannableString(binding.editText.getText());
                spannable.setSpan(foregroundColorSpan,
                        binding.editText.getSelectionStart(),
                        binding.editText.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.editText.setText(spannable);

                Settings.settings().setForegroundColorSpan(foregroundColorSpan.getForegroundColor());
                Settings.settings().saveBackgroundColorSpan(this);
            }
        });

        binding.colorBtn.setOnLongClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.color_btn_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.breenBtn:
                        binding.colorBtn.setTag(new ForegroundColorSpan(Color.GREEN));
                        return true;
                    case R.id.redBtn:
                        binding.colorBtn.setTag(new ForegroundColorSpan(Color.RED));
                        return true;
                    case R.id.blackBtn:
                        binding.colorBtn.setTag(new ForegroundColorSpan(Color.BLACK));
                        return true;
                    case R.id.blueBtn:
                        binding.colorBtn.setTag(new ForegroundColorSpan(Color.BLUE));
                        return true;
                    default:
                        return false;
                }
            });

            Settings.settings().saveForegroundColorSpan(this);
            popupMenu.show();

            return true;

        });

        binding.backgroundBtn.setTag(new BackgroundColorSpan(Color.WHITE));
        binding.backgroundBtn.setOnClickListener(view -> {
            Object tag = binding.backgroundBtn.getTag();
            if (tag instanceof BackgroundColorSpan) {

                BackgroundColorSpan backgroundColorSpan = (BackgroundColorSpan) tag;
                SpannableString spannable = new SpannableString(binding.editText.getText());
                spannable.setSpan(backgroundColorSpan,
                        binding.editText.getSelectionStart(),
                        binding.editText.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.editText.setText(spannable);

                Settings.settings().setBackgroundColor(backgroundColorSpan.getBackgroundColor());
                Settings.settings().saveBackgroundColorSpan(this);
            }
        });

        binding.backgroundBtn.setOnLongClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.background_btn_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.backgroundGreenBtn:
                        binding.backgroundBtn.setTag(new BackgroundColorSpan(Color.GREEN));
                        return true;
                    case R.id.backgroundRedBtn:
                        binding.backgroundBtn.setTag(new BackgroundColorSpan(Color.RED));
                        return true;
                    case R.id.backgroundBlackBtn:
                        binding.backgroundBtn.setTag(new BackgroundColorSpan(Color.BLACK));
                        return true;
                    case R.id.backgroundBlueBtn:
                        binding.backgroundBtn.setTag(new BackgroundColorSpan(Color.BLUE));
                        return true;
                    default:
                        return false;
                }
            });


            popupMenu.show();

            return true;

        });

        binding.styleBtn.setOnClickListener(view -> {
            Object tag = binding.styleBtn.getTag();
            if (tag instanceof StyleSpan) {

                StyleSpan styleSp = (StyleSpan) tag;
                SpannableString spannable = new SpannableString(binding.editText.getText());
                spannable.setSpan(styleSp,
                        binding.editText.getSelectionStart(),
                        binding.editText.getSelectionEnd(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.editText.setText(spannable);

                Settings.settings().setStyleSpan(styleSp.getSpanTypeId());
                Settings.settings().saveStyleSpan(this);
            }
        });

        binding.styleBtn.setOnLongClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.style_btn_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.boldBtn:
                        binding.styleBtn.setTag(new StyleSpan(Typeface.BOLD));
                        return true;
                    case R.id.italycBtn:
                        binding.styleBtn.setTag(new StyleSpan(Typeface.ITALIC));
                        return true;
                    case R.id.normalBtn:
                        binding.styleBtn.setTag(new StyleSpan(Typeface.NORMAL));
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.show();

            return true;
        });
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
            case R.id.smallFontSize:
                Settings.settings().setFontSize(14);
                binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                break;
            case R.id.mediumFontSize:
                Settings.settings().setFontSize(20);
                binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                break;
            case R.id.largeFontSize:
                Settings.settings().setFontSize(32);
                binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32
                );
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
        Settings.settings().saveFontSize(this);
        return super.onOptionsItemSelected(item);
    }
}