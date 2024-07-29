package com.rakarguntara.quizapp.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rakarguntara.quizapp.R
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

@Composable
@Preview
fun QuestionDisplay(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.navy)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            QuestionTopHeader()

        }

    }
}

@Composable
fun QuestionTopHeader(count: Int = 0, max: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(style = SpanStyle(
                    color = Color.Magenta,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )) {
                    append("Question $count/")
                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )){
                    append("$max")
                }
            }
        },
        modifier = Modifier.padding(top = 8.dp)
    )
}
