package com.dissolutegames.dadjokeapp.ui.starred

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dissolutegames.dadjokeapp.databinding.FragmentStarredBinding

class StarredFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarredBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}