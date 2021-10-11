package com.dissolutegames.dadjokeapp.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dissolutegames.dadjokeapp.services.JokeService
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    private var _isInitialized: Boolean = false

    val isLoading: LiveData<Boolean> = _isLoading

    private val _jokeText = MutableLiveData<String>().apply {
        value = ""
    }

    val jokeText: LiveData<String> = _jokeText
    fun initialize(jokeId: String?) {
        if (_isInitialized) {
            return
        }
        _isInitialized = true

        viewModelScope.launch {
            _isLoading.apply {
                value = true
            }
            try {

                val joke = JokeService.getRandomJoke()
                _jokeText.apply {
                    value = joke.Joke
                }
                _isLoading.apply {
                    value = false
                }
            } catch (e: Exception) {
                _jokeText.apply {
                    value = "Joke failed to load :("
                }
            }
        }
    }
}