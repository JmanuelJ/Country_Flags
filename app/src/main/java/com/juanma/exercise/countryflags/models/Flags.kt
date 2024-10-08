package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("alt")val alt: String,
    @SerializedName("png")val png: String,
    @SerializedName("svg")val svg: String
)