package com.juanma.exercise.countryflags.repository

import com.juanma.exercise.countryflags.api.RetrofitInstance

class CountriesRepository {
    suspend fun getCountries() = RetrofitInstance.api.getAllCountries()

    suspend fun getCountryInfo(country: String) = RetrofitInstance.api.getCountry(country)
}