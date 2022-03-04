package com.zaus_app.converter.viewmodel

import androidx.lifecycle.ViewModel
import com.zaus_app.converter.App
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val currencyListData: Flow<List<Currency>>
    val showProgressBar: Channel<Boolean>
    private var lastUpdateTime: Long = 0L

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        lastUpdateTime = System.currentTimeMillis() - interactor.getSavedTime()
        if (interactor.isFirstLaunch() || lastUpdateTime > DAY) {
            getValutes()
            interactor.setNotFirstLaunch()
        }
        currencyListData = interactor.getCurrenciesFromDB()
    }

    fun getValutes() {
        interactor.setSavedTime(System.currentTimeMillis())
        interactor.getCurrencyList()
    }

    companion object {
        const val DAY = 86400000L
    }
}