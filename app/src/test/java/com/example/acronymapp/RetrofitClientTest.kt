package com.example.acronymapp


import com.example.acronymapp.helper.RetrofitInstance
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {
    private val baseUrl = "http://www.nactem.ac.uk/software/acromine/"

    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = RetrofitInstance.getInstance
        assert(instance.baseUrl().toUrl().toString() == baseUrl)
    }
}