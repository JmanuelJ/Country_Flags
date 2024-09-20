package com.juanma.exercise.countryflags.models

data class Name(
    val common: String,
    val nativeName: Map<String, NativeNameDetail>,
    val official: String
)