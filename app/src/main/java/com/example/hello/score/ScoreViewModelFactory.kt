package com.example.hello.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

public class ScoreViewModelFactory(private val finalScore: Int) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScoreViewModelWithFactory::class.java))
        return ScoreViewModelWithFactory(finalScore) as T;
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}