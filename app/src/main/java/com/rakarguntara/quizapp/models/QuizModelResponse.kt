package com.rakarguntara.quizapp.models

import com.google.gson.annotations.SerializedName

data class QuizModelResponse(

	@field:SerializedName("QuizModelResponse")
	val quizModelResponse: List<QuizModelResponseItem?>? = null
)

data class QuizModelResponseItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("choices")
	val choices: List<String?>? = null
)
