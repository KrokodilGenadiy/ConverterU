package com.zaus_app.converter.domain

import android.util.Log
import com.google.gson.JsonObject
import com.zaus_app.converter.data.CurrencyApi
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.utils.Converter
import com.zaus_app.converter.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor( private val retrofitService: CurrencyApi) {
    fun getCurrencyList(callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getCurrencies(FILE).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.i("LOG_TAG", response.body().toString())
                val valutes = response.body()?.getAsJsonObject("Valute")
                val list = Converter.convertApiListToDTOList(valutes)
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("LOG_TAG", t.toString());
                callback.onFailure()
            }
        })
    }

    companion object {
        const val FILE = "daily_json.js"
    }
}