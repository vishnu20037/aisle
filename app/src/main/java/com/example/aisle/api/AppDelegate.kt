package com.example.aisle.api

import android.content.Context
import com.example.aisle.util.Constants
import com.example.aisle.util.Constants.PREFS_NAME
import com.example.aisle.util.Constants.TOKEN_KEY

class AppDelegate {
    companion object {
        fun saveTokenToLocalStorage(context: Context, token: String) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(TOKEN_KEY, token)
            editor.apply()
        }

        fun saveFirstLoginFlag(context: Context, isFirstLogin: Boolean) {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(Constants.IS_FIRST_LOGIN, isFirstLogin)
            editor.apply()
        }
    }
}