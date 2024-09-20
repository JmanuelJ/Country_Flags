package com.juanma.exercise.countryflags.api

import com.juanma.exercise.countryflags.models.CountriesResponseItem
import com.juanma.exercise.countryflags.models.CountryInfoResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FlagsAPI {
    @GET("all?fields=name,flags")
    suspend fun getAllCountries(): Response<MutableList<CountriesResponseItem>>

    @GET("name/{name}")
    suspend fun getCountry(@Path("name") name: String): Response<List<CountryInfoResponseItem>>
}