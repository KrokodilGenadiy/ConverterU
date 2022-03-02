package com.zaus_app.converter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaus_app.converter.App
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val currencyListData: MutableLiveData<List<Currency>> = MutableLiveData()


    fun getValutes() {
        interactor.getCurrencyList(apiCallback)
    }

    private val apiCallback = object : ApiCallback {
        override fun onSuccess(list: MutableList<Currency>) {
            currencyListData.postValue(list)
        }

        override fun onFailure() {
        }
    }

    interface ApiCallback {
        fun onSuccess(list: MutableList<Currency>)
        fun onFailure()
    }

    init {
        App.instance.dagger.inject(this)
        getValutes()
    }
}