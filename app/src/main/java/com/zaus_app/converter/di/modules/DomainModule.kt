package com.zaus_app.converter.di.modules

import android.content.Context
import com.google.gson.Gson
import com.zaus_app.converter.data.CurrencyApi
import com.zaus_app.converter.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule() {
    @Singleton
    @Provides
    fun provideInteractor(currencyApi: CurrencyApi,gson: Gson) = Interactor(retrofitService = currencyApi)
}