package com.juanma.exercise.countryflags.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanma.exercise.countryflags.repository.CountriesRepository

@Suppress("UNCHECKED_CAST")
class CountryViewModelFactory(
    val app: Application,
    private val countriesRepository: CountriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(app, countriesRepository) as T
    }
}