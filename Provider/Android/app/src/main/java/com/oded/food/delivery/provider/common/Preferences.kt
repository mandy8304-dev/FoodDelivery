package com.oded.food.delivery.provider.common

import android.content.Context

class Preferences {

    
    companion object {

        @JvmStatic
        fun getLongPreference(context: Context, key: String, aValue: Long): Long {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            return preferences.getLong(key, aValue)
        }

        @JvmStatic
        fun saveLongPreference(context: Context, key: String, aValue: Long) {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            val edit = preferences.edit()
            edit.putLong(key, aValue)
            edit.apply()
        }

        @JvmStatic
        fun getIntPreference(context: Context, key: String, aValue: Int): Int {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            return preferences.getInt(key, aValue)
        }

        @JvmStatic
        fun saveIntPreference(context: Context, key: String, aValue: Int) {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            val edit = preferences.edit()
            edit.putInt(key, aValue)
            edit.apply()
        }

        @JvmStatic
        fun getStringPreference(context: Context, key: String, aValue: String): String? {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            return preferences.getString(key, aValue)
        }

        @JvmStatic
        fun saveStringPreference(context: Context, key: String, aValue: String) {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            val edit = preferences.edit()
            edit.putString(key, aValue)
            edit.apply()
        }

        @JvmStatic
        fun getBooleanPreference(context: Context, key: String, aValue: Boolean): Boolean {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            return preferences.getBoolean(key, aValue)
        }

        @JvmStatic
        fun saveBooleanPreference(context: Context, key: String, aValue: Boolean) {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            val edit = preferences.edit()
            edit.putBoolean(key, aValue)
            edit.apply()
        }

        @JvmStatic
        fun getFloatPreference(context: Context, key: String, aValue: Float): Float {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            return preferences.getFloat(key, aValue)
        }

        @JvmStatic
        fun saveFloatPreference(context: Context, key: String, aValue: Float) {
            val preferences = context.getSharedPreferences(
                context.packageName, Context.MODE_PRIVATE
            )
            val edit = preferences.edit()
            edit.putFloat(key, aValue)
            edit.apply()
        }


    }
}