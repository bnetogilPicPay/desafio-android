package com.picpay.desafio.android.ui.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    @Test fun testEvent() {
        val scenario = activityScenarioRule.scenario
        val scenario1 = launchActivity<MainActivity>().apply {
            this.moveToState(Lifecycle.State.CREATED)

        }
    }
}