package com.example.acronymapp.activities

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class MainActivityTest {
    @Mock
    lateinit var mainActivity: MainActivity

    @Before
    fun init() {
        mainActivity = mock(MainActivity::class.java)
    }

    @Test
    fun isValidText() {
        `when`(mainActivity.isValidText("hmm")).thenReturn(true)
        val actualResult = mainActivity.isValidText("hmm")
        assertEquals(true, actualResult)
    }

    @Test
    fun isInvalidText() {
        `when`(mainActivity.isValidText("hm")).thenReturn(false)
        val actualResult = mainActivity.isValidText("hm")
        assertEquals(false, actualResult)
    }
}