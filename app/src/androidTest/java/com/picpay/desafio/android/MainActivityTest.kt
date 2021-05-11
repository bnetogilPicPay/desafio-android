package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.picpay.desafio.android.ui.activity.MainActivity
import com.picpay.desafio.android.ui.viewholder.UserListItemViewHolder
import okhttp3.internal.wait
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
class MainActivityTest {

    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))

            Thread.sleep(300)

            onView(withId(R.id.recyclerView))
                /*
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<UserListItemViewHolder>(0,
                    click()
                ))*/.check(
                    matches(
                        RecyclerViewMatchers
                            .atPosition(0, withId(R.id.name))))

//            onData(Matchers.anything())
//                .inAdapterView(withId(R.id.recyclerView))
//                .atPosition(0)
//                .check(matches(hasDescendant(Matchers.allOf(withId(R.id.name),
//                    withText("Usuario Um")))))
        }
    }

    companion object {
        private const val serverPort = 8080

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}