package com.rakarguntara.quizapp.repository

import com.rakarguntara.quizapp.models.QuizModelResponseItem
import com.rakarguntara.quizapp.network.ApiService
import com.rakarguntara.quizapp.network.DataOrException
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    private val dataResponse = DataOrException<ArrayList<QuizModelResponseItem>, Boolean, Exception>()

    suspend fun getAllQuiz(): DataOrException<ArrayList<QuizModelResponseItem>, Boolean, Exception>{
        try {
            dataResponse.loading = true
            dataResponse.data = apiService.getJson()
            if(dataResponse.data.toString().isNotEmpty()){
                dataResponse.loading = false
            }
        } catch (e: Exception){
            dataResponse.e = e
        }
        return dataResponse
    }
}