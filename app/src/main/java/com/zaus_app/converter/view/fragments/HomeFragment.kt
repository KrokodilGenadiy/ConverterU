package com.zaus_app.converter.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.FragmentHomeBinding
import com.zaus_app.converter.viewmodel.HomeFragmentViewModel
import com.zaus_app.moviefrumy.view.rv_adapters.CurrencyAdapter
import com.zaus_app.moviefrumy.view.rv_adapters.diffutils.CurrencyDiff
import com.zaus_app.moviefrumy.view.rv_adapters.itemdecorators.ItemDecorator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var currencyList = listOf<Currency>()
        set(value) {
            if (field == value) return
            field = value
            updateData(field as MutableList<Currency>)
        }
    private val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter(object : CurrencyAdapter.OnItemClickListener {
            override fun click(currency: com.zaus_app.converter.data.entity.Currency) {
                Toast.makeText(requireContext(),"clicked", Toast.LENGTH_SHORT)
            }
        })
    }
    private val viewModel: HomeFragmentViewModel by viewModels()

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
        viewModel.currencyListData.observe(viewLifecycleOwner, Observer<List<Currency>> {
            currencyList = it
        })
        initPullToRefresh()
    }

    private fun initRecycler() {
        binding.mainRecycler.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = ItemDecorator(8)
            addItemDecoration(decorator)
        }
    }

    private fun updateData(newList: MutableList<Currency>) {
        val oldList = currencyAdapter.getItems()
        val productDiff = CurrencyDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(productDiff)
        currencyAdapter.setItems(newList)
        diffResult.dispatchUpdatesTo(currencyAdapter)
    }

    private fun initPullToRefresh() {
        binding.refresh.setOnRefreshListener {
            viewModel.getValutes()
            binding.refresh.isRefreshing = false
        }
    }
}