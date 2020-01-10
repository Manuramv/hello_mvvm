package com.example.hello.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel(){

    private lateinit var wordsList : MutableList<String>
    val word = MutableLiveData<String>()
    //encapsulating the value score, now the score value can't be set from anywhere outside
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish : LiveData<Boolean> get() = _eventGameFinish


    init {
        Log.i("TAG","Gameviewmodel created::")
        resetList()
        nextWord()
        _score.value =0
    }

    companion object{
        val TIME_OVER = 0;
        val ONE_SECOND = 1000L;
        val MAX_GAME_TIME = 60000L;
    }

    private fun nextWord() {
        if(!wordsList.isEmpty())
            word.value =  wordsList.removeAt(0)
        else{
            _eventGameFinish.value = true;
        }
    }

    fun resetList(){
        wordsList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordsList.shuffle()
    }


    fun gotTheCorrectWord() {
        nextWord()
        _score.value = score.value?.plus(1)

    }

    fun skippeedWord() {
        nextWord()
        _score.value = score.value?.minus(1)

    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false;
    }
}