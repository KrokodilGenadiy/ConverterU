package com.zaus_app.converter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zaus_app.converter.R
import com.zaus_app.converter.data.entity.Currency
import com.zaus_app.converter.databinding.ActivityMainBinding
import com.zaus_app.converter.view.fragments.ConvertFragment
import com.zaus_app.converter.view.fragments.HomeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(HomeFragment(),"home")
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .commit()
    }

    fun launchConvertFragment(currency: Currency) {
        val bundle = Bundle()
        bundle.putParcelable("currency", currency)
        val fragment = checkFragmentExistence("convert") ?: ConvertFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, "convert")
            .addToBackStack("convert")
            .commit()
    }

    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

}