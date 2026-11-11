package com.example.myapplication01.util

import android.content.Context
import android.content.SharedPreferences

/**
 * 로그인한 사용자 정보를 SharedPreferences 에 저장/조회한다.
 */
object PreferenceManager {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_EMAIL = "email"
    private const val KEY_NAME = "name"
    private const val KEY_PHOTO_URL = "photo_url"

    private fun prefs(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, email: String, name: String?, photoUrl: String?) {
        prefs(context).edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_NAME, name)
            .putString(KEY_PHOTO_URL, photoUrl)
            .apply()
    }

    fun isLoggedIn(context: Context): Boolean = getEmail(context) != null

    fun getEmail(context: Context): String? = prefs(context).getString(KEY_EMAIL, null)

    fun getName(context: Context): String? = prefs(context).getString(KEY_NAME, null)

    fun getPhotoUrl(context: Context): String? = prefs(context).getString(KEY_PHOTO_URL, null)

    fun clear(context: Context) {
        prefs(context).edit().clear().apply()
    }
}
