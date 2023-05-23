package com.example.acronymapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.acronymapp.activities.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @JvmField
    @Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun editTextChanges() {
        onView(withId(R.id.editText)).perform(typeText("hmm")).check(matches(withText("hmm")))
    }

    @Test
    fun recyclerScroll() {
        editTextChanges()

        Thread.sleep(5000L)

        onView(withId(R.id.recyclerview)).inRoot(
            RootMatchers.withDecorView(
                Matchers.`is`(
                    mActivityRule.activity.window.decorView
                )
            )
        ).perform((getRVCount().let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            }))
    }


    private fun getRVCount(): Int {
        val recyclerView =
            mActivityRule.activity.findViewById<View>(R.id.recyclerview) as RecyclerView
        return recyclerView.adapter?.itemCount ?: 0
    }

}