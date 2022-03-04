package com.zaus_app.converter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.FragmentConvertBinding
import com.zaus_app.converter.viewmodel.ConvertFragmentViewModel
import java.math.BigInteger


class ConvertFragment : Fragment() {
    private var _binding: FragmentConvertBinding? = null
    private val binding get() = _binding!!
    private val currency: Currency by lazy {
       arguments?.get("currency") as Currency
    }
    private val viewModel: ConvertFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetails(currency)
        binding.convert.setOnClickListener {
            convert()
        }
        viewModel.convertionResult.observe(viewLifecycleOwner, Observer<String> {
            binding.valuteInput2.setText(it)
        })
    }

    fun setDetails(currency: Currency) {
        binding.valuteCharcode1.text = currency.CharCode
        binding.valuteName1.text = currency.Name
    }

    fun convert() {
        val nominalRus = binding.valuteInput1.text
        if (binding.valuteInput1.text.isNullOrEmpty()) {
            Toast.makeText(activity,ENTER_CURRENCY_TEXT, Toast.LENGTH_LONG).show()
            return
        }
        val maxInt: BigInteger = BigInteger.valueOf(Int.MAX_VALUE.toLong())
        val value = BigInteger(nominalRus.toString())
        if (value > maxInt) {
            Toast.makeText(activity,BIG_NUMBER, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.convert(nominalRus.toString().toInt(),currency.Value,currency.Nominal)
        binding.valuteInput2.setText(((currency.Value * nominalRus.toString().toInt()) / currency.Nominal).toString())
    }

    companion object {
        const val ENTER_CURRENCY_TEXT = "Введите кол-во валюты"
        const val BIG_NUMBER = "Номинал слишком большой"
    }

}