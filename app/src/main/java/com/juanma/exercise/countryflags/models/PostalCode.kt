package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class PostalCode(
    @SerializedName("format")val format: String,
    @SerializedName("regex")val regex: String
)
