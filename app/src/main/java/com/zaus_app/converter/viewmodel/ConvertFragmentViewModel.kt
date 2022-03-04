package com.zaus_app.converter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConvertFragmentViewModel: ViewModel() {
    val convertionResult: MutableLiveData<String> = MutableLiveData("")

    fun convert(nominalRus: Int,currencyValue: Double, currencyNominal: Int) {
        convertionResult.postValue(((currencyValue * nominalRus) / currencyNominal).toString())
    }
}