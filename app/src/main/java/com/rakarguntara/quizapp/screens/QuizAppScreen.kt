package com.rakarguntara.quizapp.screens

import androidx.compose.runtime.Composable
import com.rakarguntara.quizapp.components.Question
import com.rakarguntara.quizapp.viewmodels.QuizViewModel

@Composable
fun QuizAppScreen(viewModel: QuizViewModel) {
    Question(viewModel)
}
