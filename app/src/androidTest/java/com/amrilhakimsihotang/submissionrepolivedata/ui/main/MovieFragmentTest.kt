package com.amrilhakimsihotang.submissionrepolivedata.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.home.HomeActivity
import com.amrilhakimsihotang.submissionrepolivedata.utils.DataDummy
import com.amrilhakimsihotang.submissionrepolivedata.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieFragmentTest {
    private val dummyMovie = DataDummy.generateRemoteMovie()

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
    fun testRecyclerViewLoad() {

        onView(withId(R.id.rvmovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvmovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )

    }

    @Test
    fun testPositionMovie() {
        onView(withId(R.id.rvmovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvmovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
    }
}