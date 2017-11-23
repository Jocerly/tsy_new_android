package com.tsy.base.tool

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.annotation.StringRes

/**
 * Created by jay on 17/7/31.
 */

class PreferencesLoader(private val context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveString(@StringRes keyResId: Int, value: String) {
        val key = context.getString(keyResId)
        saveString(key, value)
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    fun saveLong(@StringRes keyResId: Int, value: Long) {
        val key = context.getString(keyResId)
        saveLong(key, value)
    }

    fun saveLong(key: String, value: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }
}
