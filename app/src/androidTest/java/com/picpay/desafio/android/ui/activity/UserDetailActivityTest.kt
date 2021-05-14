package com.picpay.desafio.android.ui.activity

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.util.EditViewMatchers
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UserDetailActivityTest {
    private val TIME_SLEEP: Long = 2000


    @get:Rule
    var mainActivity = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    private lateinit var context: Context
    private lateinit var title: String

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        title = context.resources.getString(R.string.str_details)
    }

    fun gotoUserDetailActivity() {
        // Click and goto User Detail Activity
        onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(isEnabled()))

        Thread.sleep(TIME_SLEEP)

        onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListItemViewHolder>(
                    0,
                    ViewActions.click()
                ));

        // Wait For Open The Detail View Screen
        Thread.sleep(TIME_SLEEP)
    }


    @Test
    fun testUserDetailHasVisibleTitle() {
        gotoUserDetailActivity()

        onView(withText(title))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
    }

    @Test
    fun testUserDetailNotEditable() {
        gotoUserDetailActivity()

        onView(withText(title))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))

        onView(withId(R.id.input_name)).let {
            it.check(ViewAssertions.matches(EditViewMatchers.isDisabled()))
            it.check(ViewAssertions.matches(isDisplayed()))
        }
    }

}