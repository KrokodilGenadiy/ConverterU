package com.zaus_app.converter.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.FragmentHomeBinding
import com.zaus_app.converter.view.MainActivity
import com.zaus_app.converter.viewmodel.HomeFragmentViewModel
import com.zaus_app.moviefrumy.view.rv_adapters.CurrencyAdapter
import com.zaus_app.moviefrumy.view.rv_adapters.diffutils.CurrencyDiff
import com.zaus_app.moviefrumy.view.rv_adapters.itemdecorators.ItemDecorator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var scope: CoroutineScope
    private var currencyList = listOf<Currency>()
        set(value) {
            if (field == value) return
            field = value
            updateData(field as MutableList<Currency>)
        }
    private val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter(object : CurrencyAdapter.OnItemClickListener {
            override fun click(currency: Currency) {
                (requireActivity() as MainActivity).launchConvertFragment(currency)
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
        initPullToRefresh()

        scope = CoroutineScope(Dispatchers.IO).also { scope ->
            scope.launch {
                viewModel.currencyListData.collect {
                    withContext(Dispatchers.Main) {
                        currencyList = it
                    }
                }
            }
            scope.launch {
                for (element in viewModel.showProgressBar) {
                    launch(Dispatchers.Main) {
                        binding.progressBar.isVisible = element
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
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