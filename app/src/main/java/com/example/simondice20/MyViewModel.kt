package com.example.simondice20

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.simondice20.Datos.ronda
import com.example.simondice20.Datos.secuenciaJugador
import com.example.simondice20.Datos.secuenciaMaquina
import com.example.simondice20.Datos.toastText

class MyViewModel: ViewModel() {
    fun aumentarRonda() {
        ronda.value = ronda.value + 1
    }
    fun generarSecuencia() {
        aumentarRonda()
        secuenciaMaquina.add((0..3).random())
    }
    fun click(ButtonId: Int, context: Context) {
        secuenciaJugador.add(ButtonId)
        comprobacion()
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
    }
    fun comprobacion() {
        if (secuenciaJugador.size <= secuenciaMaquina.size) {
            comprobarSecuencia()
        }
    }
    fun comprobarSecuencia() {
        if (secuenciaMaquina == secuenciaJugador){
            secuenciaJugador.clear()
            Log.d("TAG", "CORRECTO")
            setToastText("Ronda " + ronda.value + " superada")
        }
        else if (secuenciaMaquina.subList(0, secuenciaJugador.size) == secuenciaJugador){
            Log.d("TAG", "CORRECTO")
            setToastText("Vas por buen camino!!")
        }
        else{
            secuenciaJugador.clear()
            secuenciaMaquina.clear()
            ronda.value = 0
            Log.d("TAG", "INCORRECTO")
            setToastText("Ronda perdida :(")
        }
    }

    fun setToastText(text: String) {
        toastText = text
    }

    fun getRonda(): Int {
        return Datos.ronda.value
    }

    fun getSecuenciaMaquina(): MutableList<Int> {
        return Datos.secuenciaMaquina
    }

    fun getSecuenciaJugador(): MutableList<Int> {
        return Datos.secuenciaJugador
    }


}