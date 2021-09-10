package com.e.buynow.util

import android.content.Context

object IO {
    const val SHARED_PREFERENCES = "com.e.buynow.SHARED_PREFERENCES"
    const val ACCESS_TOKEN = "token"


    fun setData(
        context: Context,
        key: String?,
        value: String?
    ) {
        val editor = context.getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getData(context: Context, key: String?): String? {
        val prefs = context.getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        return prefs.getString(key, "")
    }


    fun deleteData(context: Context, key: String?) {
        val prefs = context.getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        prefs.edit().remove(key).apply()
    }


    fun getToken(ctx: Context): String? {
        val prefs = ctx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return prefs.getString(ACCESS_TOKEN, "")
    }
}