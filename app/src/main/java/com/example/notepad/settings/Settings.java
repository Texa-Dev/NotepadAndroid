package com.example.notepad.settings;

import android.content.Context;
import android.util.Log;

import com.example.notepad.R;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Settings {
    private final String fileName = "settings";
    private static Settings settings;

    public static Settings settings() {
        if (settings == null)
            settings = new Settings();
        return settings;
    }

    private Settings() {
    }

    public Settings save(Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(settings());
            outputStream.write(json.getBytes());
        } catch (Exception e) {
            Log.d("FF", "save: " + e);
        }
        return settings();
    }

    public Settings load(Context context){
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            Gson gson = new Gson();
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.nextLine();
            settings = gson.fromJson(json, Settings.class);
        } catch (Exception e) {
            Log.d("FF", "load: " + e);
        }
        return settings();
    }

    //public int theme = R.style.Theme_Green;
}
