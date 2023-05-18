package com.example.acronymapp


import com.example.acronymapp.helper.RetrofitHelper
import com.example.acronymapp.helper.RetrofitInstance
import org.junit.Test

class RetrofitClientTest {
    private val baseUrl = "http://www.nactem.ac.uk/software/acromine/"

    @Test
    fun testRetrofitInstance() {
        val instance: RetrofitHelper = RetrofitInstance.api
        //assert(instance.baseUrl().toUrl().toString() == baseUrl)
    }
}