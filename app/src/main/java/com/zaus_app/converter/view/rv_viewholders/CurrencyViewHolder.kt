package com.zaus_app.moviefrumy.view.rv_viewholders


import androidx.recyclerview.widget.RecyclerView
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.CurrencyItemBinding

class CurrencyViewHolder(binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val charCode = binding.charCode
    private val name = binding.currencyName
    private val nominal = binding.nominal
    private val currentValue = binding.currentValue
    private val previousValue = binding.previousValue


    fun bind(currency: Currency) {
        charCode.text = currency.charCode
        name.text = currency.name
        nominal.text = "Номинал: ${currency.nominal}"
        currentValue.text = "Тек. цена: ${currency.value} рос. руб."
        previousValue.text = "Пред. цена: ${currency.previous} рос. руб."
    }
}