package com.example.acronymapp.utils

import android.content.Context
import android.net.ConnectivityManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock


internal class CommonUtilsTest {
    @Mock
    lateinit var context: Context
    private lateinit var manager: ConnectivityManager

    @Before
    fun init() {
        manager = mock(ConnectivityManager::class.java)
        context = mock(Context::class.java)
    }

    @Test
    fun isOnline() {
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(manager)
        Assert.assertEquals(true, CommonUtils.isOnline(context))
    }

    @Test
    fun isNotOnline() {
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(manager)
        Assert.assertEquals(false, CommonUtils.isOnline(context))
    }
}