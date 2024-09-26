package com.juanma.exercise.countryflags.models

import com.google.gson.annotations.SerializedName

data class CapitalInfo(
    @SerializedName("latlng") val latLng: List<Double>
)
