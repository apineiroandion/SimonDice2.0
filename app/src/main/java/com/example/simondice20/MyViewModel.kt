package com.example.simondice20

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.simondice20.Datos.ronda
import com.example.simondice20.Datos.secuenciaJugador
import com.example.simondice20.Datos.secuenciaMaquina

class MyViewModel: ViewModel() {
    fun aumentarRonda() {
        ronda.value = ronda.value + 1
    }
    fun generarSecuencia() {
        aumentarRonda()
        secuenciaMaquina.add((0..3).random())
    }
    fun click(ButtonId: Int) {
        secuenciaJugador.add(ButtonId)
        comprobacion()
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
            //setToastText("Ronda " + ronda.value + " superada")
        }
        else if (secuenciaMaquina.subList(0, secuenciaJugador.size) == secuenciaJugador){
            Log.d("TAG", "CORRECTO")
            //setToastText("Vas por buen camino!!")
        }
        else{
            secuenciaJugador.clear()
            secuenciaMaquina.clear()
            ronda.value = 0
            Log.d("TAG", "INCORRECTO")
            //setToastText("Ronda perdida :(")
        }
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