package com.example.hello.game

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.hello.R

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class GameFragmentTest{

    @Test
    fun ischeckAllFieldsArePresentInTheView(){
        val fragScenario = launchFragmentInContainer<GameFragment>()
        onView(withId(R.id.gameFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.txtWordis)).check(matches(isDisplayed()))
        onView(withId(R.id.txtGuessWord)).check(matches(isDisplayed()))
        onView(withId(R.id.btnPrevios)).check(matches(isDisplayed()))
        onView(withId(R.id.btnGotIt)).check(matches(isDisplayed()))
    }

    @Test
    fun clickSkipButton(){
        val fragScenario = launchFragmentInContainer<GameFragment>()

        onView(withId(R.id.btnPrevios)).perform(ViewActions.click())
    }

}
