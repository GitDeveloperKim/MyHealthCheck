package com.example.myapplication01.util

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication01.vo.Sex

/**
 * 로그인한 사용자 정보와 회원 신체 정보를 SharedPreferences 에 저장/조회한다.
 * 신체 정보는 계정(email)별로 저장되어 로그아웃 후 다시 로그인해도 유지된다.
 */
object PreferenceManager {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_EMAIL = "email"
    private const val KEY_NAME = "name"
    private const val KEY_PHOTO_URL = "photo_url"

    private const val KEY_PROFILE_DONE = "profile_done"
    private const val KEY_AGE = "age"
    private const val KEY_HEIGHT = "height_cm"
    private const val KEY_WEIGHT = "weight_kg"
    private const val KEY_SEX = "sex"

    private fun prefs(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // ---------- 로그인 정보 ----------

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

    /** 로그아웃: 로그인 정보만 지우고, 계정별 신체 정보는 남겨둔다. */
    fun clear(context: Context) {
        prefs(context).edit()
            .remove(KEY_EMAIL)
            .remove(KEY_NAME)
            .remove(KEY_PHOTO_URL)
            .apply()
    }

    // ---------- 회원 신체 정보 (계정별) ----------

    private fun profileKey(context: Context, key: String): String =
        "profile_${getEmail(context)}_$key"

    fun isProfileCompleted(context: Context): Boolean =
        prefs(context).getBoolean(profileKey(context, KEY_PROFILE_DONE), false)

    fun saveProfile(context: Context, age: Int, heightCm: Float, weightKg: Float, sex: Sex) {
        prefs(context).edit()
            .putInt(profileKey(context, KEY_AGE), age)
            .putFloat(profileKey(context, KEY_HEIGHT), heightCm)
            .putFloat(profileKey(context, KEY_WEIGHT), weightKg)
            .putString(profileKey(context, KEY_SEX), sex.name)
            .putBoolean(profileKey(context, KEY_PROFILE_DONE), true)
            .apply()
    }

    fun getAge(context: Context): Int = prefs(context).getInt(profileKey(context, KEY_AGE), 0)

    fun getHeightCm(context: Context): Float = prefs(context).getFloat(profileKey(context, KEY_HEIGHT), 0f)

    fun getWeightKg(context: Context): Float = prefs(context).getFloat(profileKey(context, KEY_WEIGHT), 0f)

    fun getSex(context: Context): Sex? =
        prefs(context).getString(profileKey(context, KEY_SEX), null)?.let { Sex.valueOf(it) }
}
