package com.example.acronymapp.helper

import com.example.acronymapp.model.AcronymModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface RetrofitHelper {

    @GET("dictionary.py")
    fun getAcronym(
        @Query("sf") sf: String?,
        @Query("lf") lf: String?
    ): Call<List<AcronymModel?>?>?

}