package com.example.no_cloudsave.Managers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Constant {
    var baseUrl: String = "https://realtimedemoapp-3accc.firebaseio.com"
}

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(Constant.baseUrl).addConverterFactory(
                    GsonConverterFactory.create()).build()

        return retrofit as Retrofit
    }
}
