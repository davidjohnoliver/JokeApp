package com.dissolutegames.jokeapp.services

import android.content.Context
import androidx.room.Room
import com.dissolutegames.jokeapp.model.AppDatabase
import com.dissolutegames.jokeapp.model.Joke

object JokeStorageService {
    private var _appDatabase: AppDatabase? = null

    private val appDatabase get() = _appDatabase!!

    private val dao get() = appDatabase.jokeDao()

    fun initialize(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .allowMainThreadQueries()
            .build()
    }

    fun close() {
        appDatabase.close()
        _appDatabase = null
    }

    fun starJoke(joke: Joke) {
        dao.insert(joke)
    }

    fun unstarJoke(joke: Joke) {
        dao.delete(joke)
    }

    fun isStarred(joke: Joke): Boolean = dao.getJoke(joke.Id) != null

    fun getJokeById(jokeId: String) = dao.getJoke(jokeId)

    fun getAllStarredJokes(): Array<Joke> = dao.getAllJokes()
}