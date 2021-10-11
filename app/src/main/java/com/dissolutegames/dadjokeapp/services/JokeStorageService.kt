package com.dissolutegames.dadjokeapp.services

import com.dissolutegames.dadjokeapp.model.Joke

object JokeStorageService {
    private val _simpleStorage = mutableMapOf<String, Joke>()

    fun starJoke(joke: Joke) {
        _simpleStorage[joke.Id] = joke
    }

    fun unstarJoke(joke: Joke) {
        _simpleStorage.remove(joke.Id)
    }

    fun isStarred(joke: Joke): Boolean = _simpleStorage.containsKey(joke.Id)

    fun getJokeById(jokeId: String) = _simpleStorage[jokeId]

    fun getAllStarredJokes(): List<Joke> = _simpleStorage.values.toList()
}