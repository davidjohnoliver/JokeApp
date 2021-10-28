package com.dissolutegames.jokeapp.ui.starred

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dissolutegames.jokeapp.databinding.FragmentStarredBinding
import com.dissolutegames.jokeapp.model.Joke
import com.dissolutegames.jokeapp.services.JokeStorageService

class StarredFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarredBinding.inflate(layoutInflater, container, false)

        val listAdapter = StarredListAdapter(
            inflater,
            JokeStorageService.getAllStarredJokes(),
            ::onJokeItemClicked
        )
        binding.starredList.adapter = listAdapter
        binding.starredList.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    private fun onJokeItemClicked(joke: Joke) {
        findNavController().navigate(StarredFragmentDirections.navActionStarredToJoke(joke.Id))
    }
}