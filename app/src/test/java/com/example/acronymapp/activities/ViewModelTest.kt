package com.example.acronymapp.activities

import android.content.Context
import com.example.acronymapp.viewmodel.AcronymViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class ViewModelTest {

    private lateinit var mainViewModel: AcronymViewModel
    private val sf = "hmm"
    private val lf = ""


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = AcronymViewModel()
    }

    @Test
    fun `should emit error object when api response error`() {
        val message = "Error from api"
        mainViewModel.getAcronymInfo(context = MainActivity::class.java as Context, sf, lf)


    }

}