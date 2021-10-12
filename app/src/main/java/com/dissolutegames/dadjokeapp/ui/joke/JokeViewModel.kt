package com.dissolutegames.dadjokeapp.ui.joke

import androidx.lifecycle.*
import com.dissolutegames.dadjokeapp.model.Joke
import com.dissolutegames.dadjokeapp.services.JokeService
import com.dissolutegames.dadjokeapp.services.JokeStorageService
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
    private val _joke = MutableLiveData<Joke?>().apply {
        value = null
    }

    private val _isStarred = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isStarred: LiveData<Boolean> = _isStarred

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

                val joke = if (jokeId == null)  JokeService.getRandomJoke() else JokeService.getJoke(jokeId)
                _joke.apply { value = joke }
                _jokeText.apply {
                    value = joke.Joke
                }
                _isStarred.apply {
                    value = JokeStorageService.isStarred(joke)
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

    fun toggleStarred() {
        val joke = _joke.value ?: return
        val isStarred = JokeStorageService.isStarred(joke)
        if (isStarred) {
            JokeStorageService.unstarJoke(joke)
            _isStarred.apply {
                value = false
            }
        }
        else {
            JokeStorageService.starJoke(joke)
            _isStarred.apply {
                value = true
            }
        }
    }
}