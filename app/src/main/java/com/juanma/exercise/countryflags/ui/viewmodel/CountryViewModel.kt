package com.juanma.exercise.countryflags.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juanma.exercise.countryflags.models.CountriesResponseItem
import com.juanma.exercise.countryflags.models.CountryInfoResponseItem
import com.juanma.exercise.countryflags.repository.CountriesRepository
import com.juanma.exercise.countryflags.util.Resource
import kotlinx.coroutines.launch

class CountryViewModel(
    app: Application,
    private val countriesRepository: CountriesRepository
) : AndroidViewModel(app) {

    val countries: MutableLiveData<Resource<List<CountriesResponseItem>>> = MutableLiveData()
    val info: MutableLiveData<Resource<List<CountryInfoResponseItem>>> = MutableLiveData()

    init {
        getAllCountries()
    }

    fun getInfo(country: String) {
        getCountryInfo(country)
    }

    private fun getAllCountries() = viewModelScope.launch {
        try {
            val response = countriesRepository.getCountries().body()
            countries.postValue(Resource.Success(response!!))
        } catch (e: Exception) {
            e.printStackTrace()
            countries.postValue(Resource.Error(e.message ?: "Error"))
        }
    }

    private fun getCountryInfo(country: String) = viewModelScope.launch {
        try {
            val response = countriesRepository.getCountryInfo(country).body()
            info.postValue(Resource.Success(response!!))
        } catch (e: Exception) {
            e.printStackTrace()
            countries.postValue(Resource.Error(e.message ?: "Error"))
        }
    }
}