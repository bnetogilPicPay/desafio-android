package com.picpay.desafio.android.ui.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.RecyclerViewMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test fun testInContactsScreen() {
        onView(withText("Contatos")).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(isEnabled()))
        onView(withId(R.id.recyclerView)).check(matches(RecyclerViewMatchers.atPosition(0, withChild(ChildMa R.id.item_user))))
    }
}