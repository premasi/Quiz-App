package com.rakarguntara.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rakarguntara.quizapp.screens.QuizAppScreen
import com.rakarguntara.quizapp.ui.theme.QuizAppTheme
import com.rakarguntara.quizapp.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                Surface {
                    val quizViewModel: QuizViewModel by viewModels()
                    QuizAppScreen(quizViewModel)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizAppTheme {

    }
}