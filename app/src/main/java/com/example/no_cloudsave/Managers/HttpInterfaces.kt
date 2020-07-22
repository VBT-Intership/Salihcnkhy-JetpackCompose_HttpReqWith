package com.example.no_cloudsave.Managers

import com.example.no_cloudsave.Recipe
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @GET("recipes.json")
    fun getRecipes(): Call<List<Recipe>>
}


