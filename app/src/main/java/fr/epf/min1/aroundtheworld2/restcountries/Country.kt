package fr.epf.min1.aroundtheworld2.restcountries

import com.squareup.moshi.Json

data class Country(
    @Json(name = "name") val name: String,
    @Json(name = "capital") val capital: String,
    // Add other properties you need
)

