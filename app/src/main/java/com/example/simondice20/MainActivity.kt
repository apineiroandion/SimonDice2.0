package com.example.simondice20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.simondice20.ui.theme.SimonDice20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MyViewModel()
        //enableEdgeToEdge()
        setContent {
            SimonDice20Theme {
                UI(model = viewModel)
            }
        }
    }
}
