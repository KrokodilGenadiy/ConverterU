package com.zaus_app.converter.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val defaultTime = 0L

    init {
        if(preference.getBoolean(KEY_FIRST_LAUNCH, false)) {
            preference.edit { putLong(LAST_UPDATE_TIME, System.currentTimeMillis()) }
        }
    }
    fun setNotFirstLaunchFlag() {
        preference.edit { putBoolean(KEY_FIRST_LAUNCH, false) }
    }

    fun isFirstLaunch():Boolean {
        return preference.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun getLastUpdateTime(): Long {
        return preference.getLong(LAST_UPDATE_TIME,defaultTime)
    }

    fun putLastUpdateTime(time: Long){
        preference.edit { putLong(LAST_UPDATE_TIME, time) }
    }


    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val LAST_UPDATE_TIME = "update_time"
    }
}