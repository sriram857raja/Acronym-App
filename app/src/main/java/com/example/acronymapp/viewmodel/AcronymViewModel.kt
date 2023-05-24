package com.example.acronymapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronymapp.R
import com.example.acronymapp.helper.RetrofitHelper
import com.example.acronymapp.helper.RetrofitInstance
import com.example.acronymapp.model.AcronymModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AcronymViewModel : ViewModel() {


    val acronymResponse: MutableLiveData<AcronymModel> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData(false)
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Error : ${throwable.localizedMessage}")
    }

    fun getAcronymData(context: Context, sf: String, lf: String) {
        progressBar.value = true
        val api = RetrofitInstance.getInstance.create(RetrofitHelper::class.java)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = api.getAcronymInfo(sf, lf)
            withContext(Dispatchers.Main) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (it.isEmpty()) {
                                onError(context.getString(R.string.no_records))
                            } else {
                                progressBar.postValue(false)
                                acronymResponse.postValue(response.body()!![0])
                            }
                        }
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        progressBar.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}