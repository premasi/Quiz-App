package com.rakarguntara.quizapp.models

import com.google.gson.annotations.SerializedName

class QuizModelResponse : ArrayList<QuizModelResponseItem>()

data class QuizModelResponseItem(
	val answer: String,
	val category: String,
	val choices: List<String>,
	val question: String
)
