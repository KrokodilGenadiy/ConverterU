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

    fun getValutes() {
        interactor.getCurrencyList()
    }

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        if (interactor.isFirstLaunch()) {
            getValutes()
            interactor.setNotFirstLaunch()
        }
        currencyListData = interactor.getCurrenciesFromDB()
    }
}