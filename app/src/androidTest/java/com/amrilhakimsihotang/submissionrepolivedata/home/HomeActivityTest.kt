package com.amrilhakimsihotang.submissionrepolivedata.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.utils.DataDummy
import com.amrilhakimsihotang.submissionrepolivedata.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateRemoteMovie()
    private val dummyTivi = DataDummy.generateRemoteTivishow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun actionFab() {
        onView(withId(R.id.fab)).perform(click())
    }

    @Test
    fun actionMenu() {
        onView(withId(R.id.change_language)).perform(click())
    }

    @Test
    fun actionPager() {
        onView(withId(R.id.viewpager)).perform(click())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rvmovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvmovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadTivi() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvtivi)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTivi.size
            )
        )
    }
}