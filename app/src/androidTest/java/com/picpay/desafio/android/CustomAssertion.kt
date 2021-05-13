package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

class CustomAssertion {
    companion object {
        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewItemCountAssertion(count)
        }

        fun hasItemName(name: String): ViewAssertion? {
            return object : TypeSafeMatcher<UserListItemViewHolder>(), ViewAssertion {
                override fun matchesSafely(customHolder: UserListItemViewHolder): Boolean {
                    return customHolder.view.name.equals(name)
                }

                override fun describeTo(description: Description) {
                    description.appendText("item in the middle")
                }

                override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                    view?.let {

                        val i = it as UserListItemViewHolder

                        i.view.name.equals(name)
                    }
                }
            }
        }
    }

    private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view !is RecyclerView) {
                throw IllegalStateException("The asserted view is not RecyclerView")
            }

            if (view.adapter == null) {
                throw IllegalStateException("No adapter is assigned to RecyclerView")
            }

            ViewMatchers.assertThat(
                "Recycler Item isNull",
                view.adapter?.itemCount, Matchers.greaterThan(0)
            )
        }
    }



}