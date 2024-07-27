package com.rakarguntara.quizapp.network

import com.rakarguntara.quizapp.models.QuizModelResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/world.json")
    suspend fun getJson(): QuizModelResponse
}