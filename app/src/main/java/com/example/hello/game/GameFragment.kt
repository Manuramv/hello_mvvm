package com.example.hello.game

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController


import com.example.hello.R
import com.example.hello.databinding.GameFragmentBinding
import com.example.hello.title.TitleFragmentDirections

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GameFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var gameViewModel: GameViewModel
   private lateinit var binding: GameFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.btnGotIt.setOnClickListener{
            gameViewModel.gotTheCorrectWord()
        }
        binding.btnPrevios.setOnClickListener{ gameViewModel.skippeedWord() }


        gameViewModel.word.observe(this, Observer { newWord->
            binding.txtGuessWord.text = newWord
        })

        gameViewModel.score.observe(this, Observer { newSocre->
            binding.txtScore.text = newSocre.toString()
        })

        gameViewModel.eventGameFinish.observe(this, Observer { gameFinish->
            if(gameFinish){
                onGameFinished()
                //we need to call this method to prevent calling the eventgamefinish method again and again.
                //for exmple just put a toast on the onGameFinished() and try to rotate the phone once eventGameFinish occured, because while rotating the phone fragment gets recreate and the value will be recreated by livedata.
                gameViewModel.onGameFinishComplete()
            }

        })


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onGameFinished() {
       // view?.findNavController()?.navigate(GameFragmentDirections.actionGameToScore(gameViewModel.score.value!!))
        Toast.makeText(this.activity, resources.getString(R.string.game_finished_text),Toast.LENGTH_LONG).show()
    }



}
