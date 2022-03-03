package com.zaus_app.converter.domain

import com.google.gson.JsonObject
import com.zaus_app.converter.data.CurrencyApi
import com.zaus_app.converter.data.MainRepository
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.utils.Converter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: CurrencyApi) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getCurrencyList() {
        scope.launch {
            progressBarState.send(true)
        }
        retrofitService.getCurrencies(FILE).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val valutes = response.body()?.getAsJsonObject(VALUTE_JSONOBJ_NAME)
                val list = Converter.convertApiListToDTOList(valutes)
                scope.launch {
                    repo.deleteAll()
                    repo.putToDb(list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }

    fun getCurrenciesFromDB(): Flow<List<Currency>> = repo.getAllFromDB()

    companion object {
        const val FILE = "daily_json.js"
        const val VALUTE_JSONOBJ_NAME = "Valute"
    }
}