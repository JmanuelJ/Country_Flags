package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class CountriesResponseItem(
    @SerializedName("flags")val flags: Flags,
    @SerializedName("name")val name: Name
)