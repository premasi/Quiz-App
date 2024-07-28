package com.rakarguntara.quizapp.components

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.rakarguntara.quizapp.viewmodels.QuizViewModel

@Composable
fun Question(viewModel: QuizViewModel){
    val data = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true){
        CircularProgressIndicator()
    } else{
        data?.forEach { item ->
            Log.d("TAG", "Question: ${item.question}")
        }
    }
}