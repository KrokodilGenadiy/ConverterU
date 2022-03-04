package com.zaus_app.converter.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    init {
        if (preference.getBoolean(KEY_FIRST_LAUNCH, true)) {

        }
    }

    fun setNotFirstLaunchFlag() {
        preference.edit { putBoolean(KEY_FIRST_LAUNCH, false) }
    }

    fun isFirstLaunch():Boolean {
        return preference.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}