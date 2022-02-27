package com.zaus_app.converter.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.converter.data.MainRepository
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.FragmentHomeBinding
import com.zaus_app.moviefrumy.view.rv_adapters.CurrencyAdapter
import com.zaus_app.moviefrumy.view.rv_adapters.diffutils.CurrencyDiff
import com.zaus_app.moviefrumy.view.rv_adapters.itemdecorators.ItemDecorator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter(object : CurrencyAdapter.OnItemClickListener {
            override fun click(currency: com.zaus_app.converter.data.entity.Currency) {
                Toast.makeText(requireContext(),"clicked", Toast.LENGTH_SHORT)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        updateData(MainRepository.valutes as MutableList<Currency>)
    }

    fun initRecycler() {
        binding.mainRecycler.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = ItemDecorator(8)
            addItemDecoration(decorator)
        }
    }

    fun updateData(newList: MutableList<Currency>) {
        val oldList = currencyAdapter.getItems()
        val productDiff = CurrencyDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(productDiff)
        currencyAdapter.setItems(newList)
        diffResult.dispatchUpdatesTo(currencyAdapter)
    }
}