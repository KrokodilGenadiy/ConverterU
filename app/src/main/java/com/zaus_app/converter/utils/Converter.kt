package com.zaus_app.converter.utils

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.data.entity.Valutes

object Converter {
    fun convertApiListToDTOList(valutes: com.google.gson.JsonObject?): MutableList<Currency> {
        val result = mutableListOf<Currency>()
        val gson =  Gson()

        Valutes.values().forEach {
            val str = valutes?.getAsJsonObject(it.toString()).toString()
            result.add(
                gson.fromJson(str, Currency::class.java)
            )
        }
        return result
    }
}
