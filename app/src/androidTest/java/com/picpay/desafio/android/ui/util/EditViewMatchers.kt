package com.picpay.desafio.android.ui.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.Description
import org.hamcrest.Matcher


object EditViewMatchers {

    fun isEnabled() :Matcher<View> {
        return object : BoundedMatcher<View, TextInputEditText>(TextInputEditText::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("TextInputEditText Is Disable")
            }

            override fun matchesSafely(input: TextInputEditText?): Boolean {
                return input?.isEnabled == true
            }
        }
    }

    fun isDisabled() :Matcher<View> {
        return object : BoundedMatcher<View, TextInputEditText>(TextInputEditText::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("TextInputEditText Is Disable")
            }

            override fun matchesSafely(input: TextInputEditText?): Boolean {
                return input?.isEnabled == false
            }
        }
    }

    fun notIsEmpty() :Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView Not Is Empty")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount!! > 0
            }
        }
    }

    fun isEmpty() : Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView Is Empty")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount!! == 0
            }
        }
    }
}