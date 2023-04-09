package com.example.notepad.settings;

import android.content.Context;
import android.graphics.Color;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.example.notepad.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Settings {
    private final String fileName = "settings.json";
    private static Settings settings;
    private int fontSize;
    private int backgroundColor;
    private int foregroundColorSpan;
    private int styleSpan;

    public int getStyleSpan() {
        return styleSpan;
    }

    public void setStyleSpan(int styleSpan) {
        this.styleSpan = styleSpan;
    }

    public static Settings settings() {
        if (settings == null)
            settings = new Settings();
        return settings;
    }

    private Settings() {
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getForegroundColorSpan() {
        return foregroundColorSpan;
    }

    public void setForegroundColorSpan(int foregroundColorSpan) {
        this.foregroundColorSpan = foregroundColorSpan;
    }

    public Settings setFontSize(int size) {
        fontSize = size;
        return this;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Settings save(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(settings());
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return settings();
    }

    @Override
    public String toString() {
        return "Settings{" +
                "fileName='" + fileName + '\'' +
                ", fontSize=" + fontSize +
                ", backgroundColorSpan=" + backgroundColor +
                ", foregroundColorSpan=" + foregroundColorSpan +
                '}';
    }

    public Settings load(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            String json = reader.readLine();
            settings = gson.fromJson(json, Settings.class);
            reader.close();
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return settings;
    }
    public Settings saveFontSize(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(this);
            outputStream.write(json.getBytes());
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return this;
    }

    public Settings loadFontSize(Context context){
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.nextLine();
            Settings loadedSettings = gson.fromJson(json, Settings.class);
            if (loadedSettings != null) {
                this.fontSize = loadedSettings.fontSize;
            }
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return this;
    }

    public Settings saveBackgroundColorSpan(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(this);
            outputStream.write(json.getBytes());
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return this;
    }

    public Settings loadBackgroundColorSpan(Context context){
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.nextLine();
            Settings loadedSettings = gson.fromJson(json, Settings.class);
            if (loadedSettings != null) {
                this.backgroundColor = loadedSettings.backgroundColor;
            }
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return this;
    }

    public Settings saveForegroundColorSpan(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(this);
            outputStream.write(json.getBytes());
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return this;
    }

    public Settings loadForegroundColorSpan(Context context){
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.nextLine();
            Settings loadedSettings = gson.fromJson(json, Settings.class);
            if (loadedSettings != null) {
                this.foregroundColorSpan = loadedSettings.foregroundColorSpan;
            }
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return this;
    }
    public Settings saveStyleSpan(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(this);
            outputStream.write(json.getBytes());
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return this;
    }

    public Settings loadStyleSpan(Context context){
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.nextLine();
            Settings loadedSettings = gson.fromJson(json, Settings.class);
            if (loadedSettings != null) {
                this.styleSpan = loadedSettings.styleSpan;
            }
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return this;
    }



    public int getTheme() {
        return R.style.Theme_Green;
    }
}