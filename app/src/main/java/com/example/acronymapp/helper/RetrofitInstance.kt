package com.example.acronymapp.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseUrl: String = "http://www.nactem.ac.uk/software/acromine/"
    val api: RetrofitHelper by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitHelper::class.java)
    }

}