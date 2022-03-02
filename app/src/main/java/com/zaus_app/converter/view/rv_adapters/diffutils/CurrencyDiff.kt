package com.zaus_app.moviefrumy.view.rv_adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.zaus_app.converter.data.entity.Currency


class CurrencyDiff(val oldList: MutableList<Currency>, val newList: MutableList<Currency>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].ID == newList[newItemPosition].ID
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].CharCode == newList[newItemPosition].CharCode &&
                oldList[oldItemPosition].Name == newList[newItemPosition].Name &&
                oldList[oldItemPosition].Nominal == newList[newItemPosition].Nominal &&
                oldList[oldItemPosition].Value == newList[newItemPosition].Value &&
                oldList[oldItemPosition].Previous == newList[newItemPosition].Previous
    }

}