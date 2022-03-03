package com.zaus_app.moviefrumy.di.modules

import android.content.Context
import androidx.room.Room
import com.zaus_app.converter.data.MainRepository
import com.zaus_app.converter.data.dao.CurrencyDao
import com.zaus_app.converter.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideCurrencyDAO(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "currency_db"
        ).build().currencyDao()

    @Provides
    @Singleton
    fun provideRepository(currencyDao: CurrencyDao) = MainRepository(currencyDao)
}