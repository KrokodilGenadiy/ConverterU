package com.zaus_app.converter.di.modules

import android.content.Context
import com.google.gson.Gson
import com.zaus_app.converter.data.CurrencyApi
import com.zaus_app.converter.data.MainRepository
import com.zaus_app.converter.domain.Interactor
import com.zaus_app.converter.utils.PreferenceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, currencyApi: CurrencyApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository,retrofitService = currencyApi, preferences = preferenceProvider)
}