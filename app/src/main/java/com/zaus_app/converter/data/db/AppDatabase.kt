package com.zaus_app.converter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zaus_app.converter.data.dao.CurrencyDao
import com.zaus_app.converter.data.entity.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}