package com.example.acronymapp.model

import com.google.gson.annotations.SerializedName

data class AcronymModel(
    @SerializedName("sf") var sf: String? = null,
    @SerializedName("lfs") var lfs: ArrayList<Lfs> = arrayListOf()
)
