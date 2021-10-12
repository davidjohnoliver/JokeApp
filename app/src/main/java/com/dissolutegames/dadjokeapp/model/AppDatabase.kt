package com.dissolutegames.dadjokeapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Joke::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun jokeDao(): JokeDao
}