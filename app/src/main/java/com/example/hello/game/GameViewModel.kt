package com.example.hello.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel(){

    private lateinit var wordsList : MutableList<String>
    val word = MutableLiveData<String>()
    val score = MutableLiveData<Int>()
    val gameFinish = MutableLiveData<Boolean>()

    init {
        Log.i("TAG","Gameviewmodel created::")
        resetList()
        nextWord()
        score.value =0
        gameFinish.value = false
    }

    private fun nextWord() {
        if(!wordsList.isEmpty())
            word.value =  wordsList.removeAt(0)
        else{
           gameFinish.value = true
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
        score.value = score.value?.plus(1)

    }

    fun skippeedWord() {
        nextWord()
        score.value = score.value?.minus(1)

    }
}