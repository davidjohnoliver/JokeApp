package com.dissolutegames.dadjokeapp.services

import com.dissolutegames.dadjokeapp.model.Joke
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

object JokeService {
    private var _client: HttpClient? = null

    private val client get() = _client!!

    private var isClosed : Boolean = false

    private val randomJokeUrl: String = "https://icanhazdadjoke.com"

    public suspend fun getRandomJoke() : Joke {
        return client.get(randomJokeUrl)
    }

    public suspend fun getJoke(jokeId: String) : Joke {
        return JokeStorageService.getJokeById(jokeId) ?: error("Joke not found") // TODO: fall back on API
    }

    public fun initialize() {
        val userAgentStr =
            "com.DissoluteGames.DadJokeApp/1 " + System.getProperty("http.agent")
        _client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    isLenient = true
                })
            }

            install(UserAgent) {
                agent = userAgentStr
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    public fun close() {
        client.close()
        _client = null
    }


}