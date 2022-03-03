package com.zaus_app.converter.data

import com.zaus_app.converter.data.dao.CurrencyDao
import com.zaus_app.converter.data.entity.Currency
import kotlinx.coroutines.flow.Flow

class MainRepository(private val currencyDao: CurrencyDao) {
    fun putToDb(currencies: List<Currency>) = currencyDao.insertAll(currencies)
    fun getAllFromDB(): Flow<List<Currency>> = currencyDao.getCachedCurrency()
    fun deleteAll() = currencyDao.nukeTable()
}