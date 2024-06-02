package fr.epf.min1.aroundtheworld2.restcountries

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v3.1/all")
    fun getCountries(): Call<List<Country>>

    @GET("v3.1/name/{country}")
    fun getCountry(@Path("country") country: String): Call<List<Country>>
}
