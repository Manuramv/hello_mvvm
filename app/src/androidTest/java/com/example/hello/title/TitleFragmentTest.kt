package com.example.hello.title

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner


import com.example.hello.R
import com.example.hello.game.GameFragment
import junit.framework.Assert.assertEquals
import org.junit.Test


import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4ClassRunner::class)
class TitleFragmentTest {

   @Test
    fun isTitleFragmentFragmentVisible(){
        //val factory = MyFragmentFactory()
        val scenario = launchFragmentInContainer<TitleFragment>()
        onView(withId(R.id.titleFragRoot)).check(matches(isDisplayed()))
    }

    @Test
    fun clickingOnPlayButtonTakingUserToGameFragment(){

        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)

        val scenario = launchFragmentInContainer<TitleFragment>()

        // Set the NavController property on the fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(ViewMatchers.withId(R.id.btnPlay)).perform(ViewActions.click())
        verify(mockNavController).navigate(TitleFragmentDirections.actionTitleToGame())

       // assertEquals(captor.firstValue, "Test text")


        //onView(withId(R.id.txtview)).check(matches(isDisplayed()))


        val Gamescenario = launchFragmentInContainer<GameFragment>()
        onView(withId(R.id.txtview)).check(matches(isDisplayed()))

    }


}