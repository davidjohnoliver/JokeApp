package com.dissolutegames.dadjokeapp.model;

import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;

@Serializable
data class Joke(
    @SerialName("id")
    val Id: String,
    @SerialName("joke")
    val Joke: String,
    @SerialName("status")
    val Status: Int
)
