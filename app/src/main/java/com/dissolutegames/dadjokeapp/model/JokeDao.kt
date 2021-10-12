package com.dissolutegames.dadjokeapp.model

import androidx.room.*

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(joke: Joke)

    @Delete
    fun delete(joke: Joke)

    @Query("SELECT * FROM Joke")
    fun getAllJokes(): Array<Joke>

    @Query("SELECT * FROM joke WHERE id LIKE :jokeId LIMIT 1")
    fun getJoke(jokeId: String) : Joke?
}