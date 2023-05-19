package com.example.acronymapp.helper

import com.example.acronymapp.model.AcronymModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitHelper {
    @GET("dictionary.py")
    suspend fun getAcronymInfo(
        @Query("sf") sf: String?,
        @Query("lf") lf: String?
    ): Response<List<AcronymModel?>?>?
}