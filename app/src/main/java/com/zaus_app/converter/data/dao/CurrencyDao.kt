package com.zaus_app.converter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaus_app.converter.data.entity.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM cached_currency")
    fun getCachedCurrency(): Flow<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Currency>)

    @Query("DELETE FROM cached_currency")
    fun nukeTable()
}