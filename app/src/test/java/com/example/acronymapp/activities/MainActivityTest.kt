package com.example.acronymapp.activities

import android.content.Context
import androidx.test.core.app.ActivityScenario
import com.example.acronymapp.utils.CommonUtils
import com.example.acronymapp.viewmodel.AcronymViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class MainActivityTest {
    @Mock
    lateinit var mainActivity: MainActivity
    lateinit var acronymViewModel: AcronymViewModel

    @Before
    fun init() {
        mainActivity = mock(MainActivity::class.java)
        acronymViewModel = AcronymViewModel()
    }

    @Test
    fun isValidText() {
        val mockUtil = mock(MainActivity::class.java)
        `when`(mockUtil.isValidText("hmm")).thenReturn(true)
        val actualResult = mockUtil.isValidText("hmm")
        assertEquals(true, actualResult)
    }

    @Test
    fun isInvalidText() {
        val mockUtil = mock(MainActivity::class.java)
        `when`(mockUtil.isValidText("hm")).thenReturn(false)
        val actualResult = mockUtil.isValidText("hm")
        assertEquals(false, actualResult)
    }

    @Test
    fun isOnline() {
        assertEquals(true, CommonUtils.isOnline(mainActivity as Context))
    }

    @Test
    fun isNotOnline() {
        assertEquals(false, CommonUtils.isOnline(mainActivity as Context))
    }
}