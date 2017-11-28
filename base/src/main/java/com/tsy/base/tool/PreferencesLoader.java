package com.tsy.base.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;

/**
 * Created by jay on 17/7/31.
 */

public class PreferencesLoader {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PreferencesLoader(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveString(@StringRes int keyResId, String value) {
        String key = context.getString(keyResId);
        saveString(key, value);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveLong(@StringRes int keyResId, long value) {
        String key = context.getString(keyResId);
        saveLong(key, value);
    }

    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0L);
    }
}
