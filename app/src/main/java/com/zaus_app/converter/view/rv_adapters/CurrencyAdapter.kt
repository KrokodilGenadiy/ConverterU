package com.zaus_app.moviefrumy.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.CurrencyItemBinding
import com.zaus_app.moviefrumy.view.rv_viewholders.CurrencyViewHolder

class CurrencyAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<Currency>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrencyViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    fun getItems(): MutableList<Currency> {
        return items
    }

    fun setItems(list: MutableList<Currency>) {
        this.items = list
    }

    interface OnItemClickListener {
        fun click(currency: Currency)
    }
}