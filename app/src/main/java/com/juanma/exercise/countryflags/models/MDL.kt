package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class MDL(
    @SerializedName("name")val name: String,
    @SerializedName("symbol")val symbol: String
)
