package com.example.acronymapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronymapp.R
import com.example.acronymapp.helper.RetrofitHelper
import com.example.acronymapp.helper.RetrofitInstance
import com.example.acronymapp.model.AcronymModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcronymViewModel : ViewModel() {


    val acronymResponse: MutableLiveData<AcronymModel> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData(false)

    fun getAcronymInfo(context: Context, sf: String, lf: String) {

        val api = RetrofitInstance.getInstance.create(RetrofitHelper::class.java)

        api.getAcronym(sf, lf)?.enqueue(object : Callback<List<AcronymModel?>?> {
            override fun onResponse(
                call: Call<List<AcronymModel?>?>, response: Response<List<AcronymModel?>?>
            ) {
                if (response.body() != null) {
                    response.body()?.let {
                        if (it.isEmpty()) {
                            errorMessage.value = context.getString(R.string.no_records)
                        } else {
                            acronymResponse.value = response.body()!![0]
                        }
                    }
                } else {
                    errorMessage.value = context.getString(R.string.no_records)
                }
            }

            override fun onFailure(call: Call<List<AcronymModel?>?>, t: Throwable) {
                errorMessage.value = t.message.toString()
            }

        })
    }


}