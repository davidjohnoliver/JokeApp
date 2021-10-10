package com.dissolutegames.dadjokeapp.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dissolutegames.dadjokeapp.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {
    val args: JokeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentJokeBinding.inflate(inflater, container, false)

        binding.dummyText.text = "Individual joke = ${args.jokeId}"

        return binding.root
    }
}