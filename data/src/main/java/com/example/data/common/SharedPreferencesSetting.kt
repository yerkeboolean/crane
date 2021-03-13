package com.example.data.common;

import android.content.Context

class SharedPreferencesSetting(applicationContext: Context) {
    private val sharedPreferences = applicationContext.getSharedPreferences(
        SESSION_FILE_NAME_WEB,
        Context.MODE_PRIVATE
    )

    var firstName
        get() = sharedPreferences.getString(USER_FIRST_NAME, "")
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(USER_FIRST_NAME, value)
            editor.apply()
        }

    var secondName
        get() = sharedPreferences.getString(USER_SECOND_NAME, "")
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(USER_SECOND_NAME, value)
            editor.apply()
        }

    var lastName
        get() = sharedPreferences.getString(USER_LAST_NAME, "")
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(USER_LAST_NAME, value)
            editor.apply()
        }

    var jobPosition
        get() = sharedPreferences.getString(USER_JOB_POSITION, "")
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(USER_JOB_POSITION, value)
            editor.apply()
        }

    companion object {
        private const val SESSION_FILE_NAME_WEB = "SESSION_WEB"
        private const val USER_FIRST_NAME = "USER_FIRST_NAME"
        private const val USER_SECOND_NAME = "USER_SECOND_NAME"
        private const val USER_LAST_NAME = "USER_LAST_NAME"
        private const val USER_JOB_POSITION = "USER_JOB_POSITION"

    }
}