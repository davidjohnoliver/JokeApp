package com.dissolutegames.dadjokeapp.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dissolutegames.dadjokeapp.R
import com.dissolutegames.dadjokeapp.databinding.FragmentJokeBinding
import com.dissolutegames.dadjokeapp.services.JokeService

class JokeFragment : Fragment() {
    val args: JokeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentJokeBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)

        viewModel.jokeText.observe(viewLifecycleOwner, {
            binding.jokeText.text = it
        })


        if (args.jokeId == getString(R.string.const_random_joke)) {
            viewModel.init(null)
        }
        else {
            viewModel.init(args.jokeId)
        }

        return binding.root
    }
}