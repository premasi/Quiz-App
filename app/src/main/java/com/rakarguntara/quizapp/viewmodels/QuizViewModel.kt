package com.rakarguntara.quizapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakarguntara.quizapp.models.QuizModelResponseItem
import com.rakarguntara.quizapp.network.DataOrException
import com.rakarguntara.quizapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuizModelResponseItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuiz()
    }

    private fun getAllQuiz(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuiz()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}