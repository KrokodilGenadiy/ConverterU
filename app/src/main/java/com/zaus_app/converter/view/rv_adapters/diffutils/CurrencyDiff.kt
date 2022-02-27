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
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].charCode == newList[newItemPosition].charCode &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].nominal == newList[newItemPosition].nominal &&
                oldList[oldItemPosition].value == newList[newItemPosition].value &&
                oldList[oldItemPosition].previous == newList[newItemPosition].previous
    }

}