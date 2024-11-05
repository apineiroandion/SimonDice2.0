package com.example.simondice20

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
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
fun createButton(color: Colors, context: Context, myViewModel: MyViewModel){
    val buttonColor by remember { mutableStateOf(color.color) }
    var _activo by remember { mutableStateOf(myViewModel.estadoLiveData.value!!.boton_activo) }

    myViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        // Log.d(TAG_LOG, "Oserver Estado: ${miViewModel.estadoLiveData.value!!.name}")
        _activo = myViewModel.estadoLiveData.value!!.boton_activo
    }

    Button(
        onClick = {
            if(_activo) myViewModel.click(color.id, context) },
        modifier = Modifier
            .padding(10.dp)
            .size(150.dp, 100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
        )
    ) {
        Text(text = color.nombre)
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, MyViewModel: MyViewModel) {
    val context = LocalContext.current
    val redButtonColor = remember { mutableStateOf(Colors.RED.color) }
    val blueButtonColor = remember { mutableStateOf(Colors.BLUE.color) }
    val greenButtonColor = remember { mutableStateOf(Colors.GREEN.color) }
    val yellowButtonColor = remember { mutableStateOf(Colors.YELLOW.color) }
    suspend fun colorearSecuencia (){
        for (i in secuenciaMaquina){
            delay(300)
            when(i){
                Colors.RED.id -> {
                    redButtonColor.value = Colors.RED.colorPressed
                    delay(1000)
                    redButtonColor.value = Colors.RED.color
                }
                Colors.BLUE.id -> {
                    blueButtonColor.value = Colors.BLUE.colorPressed
                    delay(1000)
                    blueButtonColor.value = Colors.BLUE.color
                }
                Colors.GREEN.id -> {
                    greenButtonColor.value = Colors.GREEN.colorPressed
                    delay(1000)
                    greenButtonColor.value = Colors.GREEN.color
                }
                Colors.YELLOW.id -> {
                    yellowButtonColor.value = Colors.YELLOW.colorPressed
                    delay(1000)
                    yellowButtonColor.value = Colors.YELLOW.color
                }
            }
        }
        MyViewModel.estadoJugando()
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
                //createButton(color = Colors.RED, context = context, myViewModel = MyViewModel)
                Button(onClick = { MyViewModel.click(Colors.RED.id, context) },
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
                Button(onClick = { MyViewModel.click(Colors.BLUE.id, context) },
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
                Button(onClick = { MyViewModel.click(Colors.GREEN.id, context) },
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
                Button(onClick = { MyViewModel.click(Colors.YELLOW.id, context) },
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
        Spacer(modifier = Modifier.weight(1f))
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