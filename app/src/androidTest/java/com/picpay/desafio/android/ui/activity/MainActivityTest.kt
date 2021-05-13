package com.picpay.desafio.android.ui.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.picpay.desafio.android.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var ruleScenario = ActivityScenarioRule<MainActivity>(MainActivity::class.java)
    @get:Rule
    var rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test fun test_contactsScreen_hasRecyclerViewDisplayed() {
        onView(withText("Contatos"))
            .check(matches(isCompletelyDisplayed()))
        IdlingRegistry.getInstance().register(rule.activity.getIdlingResource())
        while(!rule.activity.getIdlingResource().isIdleNow)
        onView(withId(R.id.recyclerView))
            .check(matches(isEnabled()))
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }
}