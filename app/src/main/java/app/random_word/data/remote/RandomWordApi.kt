package app.random_word.data.remote

import retrofit2.http.GET

interface RandomWordApi {

    @GET("word")
    suspend fun getRandomWord(): List<String>

}