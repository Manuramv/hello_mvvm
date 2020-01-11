package com.example.hello.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel() :ViewModel() {
    /*we are going to use the view model for updating the score.
    we can do it in 2 ways
    1, user the settter in scoreviewmodel and call it on the oncreate of fragment and pass the score.
    2, use the constructor in viewmodel, but for this we need a factory class to use the constructor invocation.*/
    private val TAG = ScoreViewModel::class.java.canonicalName
    private val _gameScoreOfUser = MutableLiveData<String>()
    val gameScoreOfUser : LiveData<String> get() = _gameScoreOfUser


    init {
        Log.i(TAG, "scoreview mode initalised:::")
    }

    //this is the first approach setting the game score via setter, fragment is passing the args to the viewmodel and viewmodel will set this.
    fun setTheGaemScore(score: String){
        _gameScoreOfUser.value = score
    }



}