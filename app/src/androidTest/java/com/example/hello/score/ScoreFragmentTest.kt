package com.example.hello.score

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.hello.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ScoreFragmentTest{


    val score = 18

    //Insteading seting the fragment scenario inside every test. Init the necessary things inside a method and use annotate @Before so
    //it will cal before every test runs
    @Before
    fun initValues(){
        val bundle = Bundle()
        bundle.putInt("score",score)
        val fragScenario = launchFragmentInContainer<ScoreFragment>(bundle)
    }

    @Test
    fun isAllFieldPresentInTheView(){
        onView(withId(R.id.scoreFragmentRoot)).check(matches(isDisplayed()))
        onView(withId(R.id.txtYourScorelbl)).check(matches(isDisplayed()))
        onView(withId(R.id.txtGameScore)).check(matches(isDisplayed()))
    }

    @Test
    fun IsShowingTheCorrectScoreFromBundle(){

        onView(withId(R.id.txtGameScore)).check(matches(withText(score.toString())))
    }
}