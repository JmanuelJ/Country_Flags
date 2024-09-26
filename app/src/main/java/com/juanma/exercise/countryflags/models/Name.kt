package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")val common: String,
    @SerializedName("nativeName")val nativeName: Map<String, NativeNameDetail>,
    @SerializedName("official")val official: String
)