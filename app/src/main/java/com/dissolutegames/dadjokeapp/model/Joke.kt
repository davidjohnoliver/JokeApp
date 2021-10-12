package com.dissolutegames.dadjokeapp.model;

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;

@Entity
@Serializable
data class Joke(
    @PrimaryKey
    @SerialName("id")
    val Id: String,
    @SerialName("joke")
    val Joke: String,
    @SerialName("status")
    val Status: Int
)
