package com.example.hello.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel(){
    companion object{
        val TIME_OVER = 0;
        val ONE_SECOND = 1000L;
        val MAX_GAME_TIME = 60000L;
    }

    private lateinit var wordsList : MutableList<String>
    val word = MutableLiveData<String>()
    val countDownTimer = MutableLiveData<String>()
    private val timer: CountDownTimer

    //encapsulating the value score, now the score value can't be set from anywhere outside
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish : LiveData<Boolean> get() = _eventGameFinish


    init {
        Log.i("TAG","Gameviewmodel created::")
        resetList()
        nextWord()
        startTheTimer()
        _score.value =0

        timer = object: CountDownTimer(MAX_GAME_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                countDownTimer.value =  DateUtils.formatElapsedTime(millisUntilFinished/ ONE_SECOND);
            }

            override fun onFinish() {
                countDownTimer.value = TIME_OVER.toString();
                _eventGameFinish.value = true;
            }
        }
        timer.start()
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
            "bag"
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
    //this method will creaet the timer
    private fun startTheTimer() {

    }


    fun onGameFinishComplete() {
        _eventGameFinish.value = false;
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}