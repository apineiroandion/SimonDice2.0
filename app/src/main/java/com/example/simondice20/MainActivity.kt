package com.example.simondice20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simondice20.ui.theme.SimonDice20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MyViewModel = MyViewModel()

        //enableEdgeToEdge()
        setContent {
            SimonDice20Theme {
                com.example.simondice20.UI(model = viewModel)
            }
        }
    }
}
