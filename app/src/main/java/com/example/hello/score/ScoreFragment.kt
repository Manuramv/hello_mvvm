package com.example.hello.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

import com.example.hello.R
import com.example.hello.databinding.FragmentScoreBinding
import com.example.hello.databinding.FragmentTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentScoreBinding
    private lateinit var scoreViewModel : ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    private lateinit var scoreViewModelForTestOtherApproach : ScoreViewModelWithFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /* We can update the score in 3 ways
    1, Legacy approach, this is a old method passing the parameter to UI and that is setting the value to textfield ( ignoring ViewModel).

    and the other 2 updates we are updating the score using viewmodel
    we are going to use the view model for updating the score.

    2, user the settter in scoreviewmodel and call it on the oncreate of fragment and pass the score.
    3, use the constructor in viewmodel, but for this we need a factory class to use the constructor invocation.
    */


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        //**************** First approach ******************
        //this is a old method passing the parameter to UI and that is setting the value to textfield,
        binding.txtGameScore.text = ScoreFragmentArgs.fromBundle(arguments!!).score.toString()

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        //**************** second approach ******************
        //using the setter to set pass the value to view model and using the observer as usual
        scoreViewModel.setTheGaemScore(ScoreFragmentArgs.fromBundle(arguments!!).score.toString())
        scoreViewModel.gameScoreOfUser.observe(this, Observer { finalScore->
            binding.txtGameScore2.text = finalScore
        })


        //**************** Third approach ******************
        //passing the score in constructor using the viewmodel factory

        //invoking viewmodel factory
        scoreViewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)

        //creating viewmodel
        scoreViewModelForTestOtherApproach = ViewModelProviders.of(this,scoreViewModelFactory).get(ScoreViewModelWithFactory::class.java)

        //observer to set the factory returned value
        scoreViewModelForTestOtherApproach.gameScoreFromConstructor.observe(this, Observer { gameScoreFromConstructor->
            binding.txtGameScore3.text = gameScoreFromConstructor.toString()
        })


        return binding.root
    }



}
