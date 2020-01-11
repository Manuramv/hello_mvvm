package com.example.hello.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/*we are going to use the view model for updating the score.
    we can do it in 2 ways
    1, user the settter in scoreviewmodel and call it on the oncreate of fragment and pass the score.
    2, use the constructor in viewmodel, but for this we need a factory class to use the constructor invocation.*/
//this is with factory approach
class ScoreViewModelWithFactory(finalScore : Int ) : ViewModel(){
    private val _gameScoreFromConstructor = MutableLiveData<Int>()
    val gameScoreFromConstructor :LiveData<Int> get() = _gameScoreFromConstructor

    private val TAG = ScoreViewModelWithFactory::class.java.canonicalName
    init {
        Log.i(TAG, "ScoreViewModelWithFactory initalised:::")
        _gameScoreFromConstructor.value = finalScore
    }
}