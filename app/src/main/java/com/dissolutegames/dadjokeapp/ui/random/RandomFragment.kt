package com.dissolutegames.dadjokeapp.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dissolutegames.dadjokeapp.databinding.FragmentRandomBinding

class RandomFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRandomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}