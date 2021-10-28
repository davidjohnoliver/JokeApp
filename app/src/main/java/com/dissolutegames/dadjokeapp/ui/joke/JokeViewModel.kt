package com.dissolutegames.jokeapp.ui.joke

import android.content.Intent
import androidx.lifecycle.*
import com.dissolutegames.jokeapp.model.Joke
import com.dissolutegames.jokeapp.services.JokeService
import com.dissolutegames.jokeapp.services.JokeStorageService
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    private var _isInitialized: Boolean = false

    val isLoading: LiveData<Boolean> = _isLoading

    private val _jokeTextQuestionless = MutableLiveData<String>().apply {
        value = ""
    }
    val jokeTextQuestionless: LiveData<String> = _jokeTextQuestionless

    private val _jokeTextQuestion = MutableLiveData<String>().apply {
        value = ""
    }
    val jokeTextQuestion: LiveData<String> = _jokeTextQuestion

    private val _jokeTextAnswer = MutableLiveData<String>().apply {
        value = ""
    }
    val jokeTextAnswer: LiveData<String> = _jokeTextAnswer


    private val _joke = MutableLiveData<Joke?>().apply {
        value = null
    }

    val joke: LiveData<Joke?> = _joke

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

                val joke =
                    if (jokeId == null) JokeService.getRandomJoke() else JokeService.getJoke(jokeId)
                _joke.apply { value = joke }

                val qIndex = joke.Joke.indexOf('?')
                var questionless = ""
                var question = ""
                var answer = ""
                if (qIndex == -1
                    // p
                    || qIndex == joke.Joke.length - 1
                ) {
                    questionless = joke.Joke
                    question = ""
                    answer = ""
                } else {
                    questionless = ""
                    question = joke.Joke.substring(0..qIndex)
                    answer = joke.Joke.substring(qIndex + 1).trim()
                }
                _jokeTextQuestionless.apply {
                    value = questionless
                }
                _jokeTextQuestion.apply { value = question }
                _jokeTextAnswer.apply { value = answer }
                _isStarred.apply {
                    value = JokeStorageService.isStarred(joke)
                }
                _isLoading.apply {
                    value = false
                }
            } catch (e: Exception) {
                _jokeTextQuestionless.apply {
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
        } else {
            JokeStorageService.starJoke(joke)
            _isStarred.apply {
                value = true
            }
        }
    }
}