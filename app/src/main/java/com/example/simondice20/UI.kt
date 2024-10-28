package com.example.simondice20

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice20.Datos.secuenciaMaquina
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UI(model: MyViewModel) {
    Greeting(MyViewModel = model)
}

@Composable
fun Greeting(modifier: Modifier = Modifier, MyViewModel: MyViewModel) {
    val redButtonColor = remember { mutableStateOf(Color.Red) }
    val blueButtonColor = remember { mutableStateOf(Color.Blue) }
    val greenButtonColor = remember { mutableStateOf(Color.Green) }
    val yellowButtonColor = remember { mutableStateOf(Color.Yellow) }
    suspend fun colorearSecuencia (){
        for (i in secuenciaMaquina){
            when(i){
                Colors.RED.id -> {
                    redButtonColor.value = Colors.RED.colorPressed
                    delay(1000)
                    redButtonColor.value = Color.Red
                }
                Colors.BLUE.id -> {
                    blueButtonColor.value = Colors.BLUE.colorPressed
                    delay(1000)
                    blueButtonColor.value = Color.Blue
                }
                Colors.GREEN.id -> {
                    greenButtonColor.value = Colors.GREEN.colorPressed
                    delay(1000)
                    greenButtonColor.value = Color.Green
                }
                Colors.YELLOW.id -> {
                    yellowButtonColor.value = Colors.YELLOW.colorPressed
                    delay(1000)
                    yellowButtonColor.value = Color.Yellow
                }
            }
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){
        Text(
            text = "SIMON DICE ",
            fontSize = 38.sp,
            modifier = Modifier.padding(vertical = 100.dp),
        )
        Row {
            Column {
                Button(onClick = { MyViewModel.click(Colors.RED.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = redButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.RED.nombre)
                }
                Button(onClick = { MyViewModel.click(Colors.BLUE.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.BLUE.nombre)
                }
            }
            Column {
                Button(onClick = { MyViewModel.click(Colors.GREEN.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.GREEN.nombre)
                }
                Button(onClick = { MyViewModel.click(Colors.YELLOW.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = yellowButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.YELLOW.nombre)
                }
            }
        }
        val coroutineScope = rememberCoroutineScope()
        TextButton(onClick = {
            coroutineScope.launch {
                MyViewModel.generarSecuencia()
                colorearSecuencia()
            }
        },
            modifier = Modifier
                .padding(10.dp)
                .size(300.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White,
            )
        ) {
            Text(text = "START ronda: " + Datos.ronda.value)
        }
    }
}