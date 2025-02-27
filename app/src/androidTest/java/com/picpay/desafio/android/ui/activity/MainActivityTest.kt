package com.picpay.desafio.android.ui.activity

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.util.RecyclerViewMatchers
import com.picpay.desafio.android.ui.util.RecyclerViewMatchers.atPosition
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    private val TIME_SLEEP: Long = 5000

    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()

    private lateinit var context: Context
    private lateinit var title: String

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        title = context.resources.getString(R.string.title)
    }

    @Test
    fun testHasVisibleTitle() {
        onView(withText(title))
            .check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun testContactsScreenHasListDisplayed() {
        onView(withText(title))
            .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.recyclerView)).let {
            it.check(matches(isEnabled()))

            Thread.sleep(TIME_SLEEP)

            it.check(matches(isCompletelyDisplayed()))
        }
    }

    @Test
    fun testContactsScreenHasListNotIsEmpty() {

        onView(withText(context.resources.getString(R.string.title)))
            .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.recyclerView))
            .check(matches(isEnabled()))

        Thread.sleep(TIME_SLEEP)

        onView(withId(R.id.recyclerView))
            .check(matches(RecyclerViewMatchers.notIsEmpty()))
    }

    @Test
    fun testContactsScreenClickFirstItem() {

        onView(withText(title))
            .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.recyclerView))
            .check(matches(isEnabled()))

        Thread.sleep(TIME_SLEEP)

        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<UserListItemViewHolder>(
                    0,
                    click()
                )
            );

        Thread.sleep(TIME_SLEEP)
    }
}