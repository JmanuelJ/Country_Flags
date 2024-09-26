package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class NativeNameDetail(
    @SerializedName("common")val common: String,
    @SerializedName("official")val official: String
)
