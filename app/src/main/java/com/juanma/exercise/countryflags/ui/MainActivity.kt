package com.juanma.exercise.countryflags.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.juanma.exercise.countryflags.databinding.ActivityMainBinding
import com.juanma.exercise.countryflags.repository.CountriesRepository
import com.juanma.exercise.countryflags.ui.viewmodel.CountryViewModel
import com.juanma.exercise.countryflags.ui.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel(){
        val countriesRepository = CountriesRepository()
        val viewModelProviderFactory = CountryViewModelFactory(application, countriesRepository)

        countryViewModel = ViewModelProvider(this, viewModelProviderFactory)[CountryViewModel::class.java]
    }
}