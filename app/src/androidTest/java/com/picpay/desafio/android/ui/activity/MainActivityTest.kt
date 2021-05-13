package com.picpay.desafio.android.ui.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.picpay.desafio.android.CustomAssertion.Companion.hasItemName
import com.picpay.desafio.android.R
import com.picpay.desafio.android.RecyclerViewMatchers
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.equalTo
import org.hamcrest.collection.IsMapContaining
import org.hamcrest.core.Is.`is`
import org.hamcrest.Matchers.hasEntry

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
        Espresso.registerIdlingResources(rule.activity.espressoTestIdlingResource)
        while(!rule.activity.espressoTestIdlingResource.isIdleNow)
        onView(withId(R.id.recyclerView))
            .check(matches(isEnabled()))
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem_first_contact() {
        onView(withId(R.id.recyclerView)).let {
            it.check(matches(isEnabled()))
        }
    }
}