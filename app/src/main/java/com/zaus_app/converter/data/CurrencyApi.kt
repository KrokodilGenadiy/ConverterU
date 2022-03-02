package com.zaus_app.converter.data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("/{currencies}")
    fun getCurrencies(
        @Path("currencies") currencies: String
    ): Call<JsonObject>
}