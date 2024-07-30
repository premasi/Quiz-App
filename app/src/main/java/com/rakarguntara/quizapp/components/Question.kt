package com.rakarguntara.quizapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rakarguntara.quizapp.R
import com.rakarguntara.quizapp.models.QuizModelResponseItem
import com.rakarguntara.quizapp.viewmodels.QuizViewModel

@Composable
fun Question(viewModel: QuizViewModel){
    val data = viewModel.data.value.data?.toMutableList()
    val quizIndex = remember {
        mutableIntStateOf(0)
    }
    if (viewModel.data.value.loading == true){
        CircularProgressIndicator()
    } else{
        val quiz = try {
            data?.get(quizIndex.intValue)
        } catch (e: Exception){
            null
        }
        if(data != null){
            QuestionDisplay(quiz!!, quizIndex, data.size, viewModel){
                quizIndex.intValue++
            }
        }
    }
}

@Composable
fun QuestionDisplay(
    item: QuizModelResponseItem,
    index: MutableIntState,
    size: Int,
    viewModel: QuizViewModel,
    onNextClick: (Int) -> Unit = {}){
    val choiceState = remember(item) {
        item.choices.toMutableList()
    }
    val answerState = remember(item) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember(item) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(item) {
        {
            answerState.value = it
            correctAnswerState.value = choiceState[it] == item.answer
        }

    }
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f,10f), 0f)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.navy)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
//            if(index.intValue >= 3) ProgressBarQuiz(score = index.intValue)
            QuestionTopHeader(index.intValue, size)
            DotDivider(pathEffect = pathEffect)

            Column(modifier = Modifier.padding(top = 32.dp)) {
                Text(text = item.question,
                    maxLines = 10,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                choiceState.forEachIndexed{index, answer ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color.Transparent)
                        .border(width = 4.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.red),
                                colorResource(R.color.red)
                        )),
                        shape = RoundedCornerShape(8.dp)),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected =(answerState.value == index),
                            onClick = {
                                updateAnswer(index)
                            }, modifier = Modifier.padding(horizontal = 8.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = if(correctAnswerState.value == true && index == answerState.value){
                                    Color.Green.copy(0.2f)
                                } else {
                                    Color.Red.copy(0.2f)
                                }
                            ))

                        Text(buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                    color = if (correctAnswerState.value == true && index == answerState.value){
                                        Color.Gray.copy(1f)
                                    } else if (correctAnswerState.value == false && index == answerState.value){
                                        Color.Red.copy(1f)
                                    } else {
                                        Color.White
                                    }
                            ,
                                    fontSize = 16.sp)
                            ){
                                append(answer)
                            }
                        })

                    }

                }

                Button(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.orange))
                    , onClick = {
                        onNextClick(index.value)

                }){
                    Text("Next", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    ))
                }
            }

        }

    }
}

@Composable
fun DotDivider(pathEffect: PathEffect){
    Canvas(modifier = Modifier.fillMaxWidth().height(1.dp).padding(vertical = 8.dp)){
        drawLine(color = Color.Magenta,
            start = Offset(x = 0f, y = 0f),
            end = Offset(size.width, y=0f),
            pathEffect = pathEffect
            )
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

@Preview
@Composable
fun ProgressBarQuiz(score: Int = 12){
    val progressFactor = remember(score) {
        mutableFloatStateOf(score*0.005f)
    }
    Row(modifier = Modifier.fillMaxWidth()
        .border(4.dp, Brush.linearGradient(colors = listOf(Color.White, Color.White)),
            shape = RoundedCornerShape(16.dp)
        )
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Button(
            modifier = Modifier.fillMaxWidth(progressFactor.floatValue)
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
                .background(brush = Brush.linearGradient(listOf(
                    Color.Red, Color.Cyan, Color.Magenta
                )))
            ,
            enabled = false,
            onClick = {}, contentPadding = PaddingValues(1.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                containerColor = Color.Transparent
            )

            ){

        }
    }
}
