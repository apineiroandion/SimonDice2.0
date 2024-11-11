package com.example.simondice20

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simondice20.Datos.isPrinted
import com.example.simondice20.Datos.ronda
import com.example.simondice20.Datos.secuenciaJugador
import com.example.simondice20.Datos.secuenciaMaquina
import com.example.simondice20.Datos.toastText
import com.example.simondice20.Estados.*


class MyViewModel: ViewModel() {
    var estadoLiveData: MutableLiveData<Estados> = MutableLiveData(ESPERANDO)

    private fun aumentarRonda() {
        ronda.value += 1
    }
    fun generarSecuencia() {
        aumentarRonda()
        secuenciaMaquina.add((0..3).random())
        isPrinted.value = false
        estadoGenerando()
    }
    fun click(buttonId: Int, context: Context) {
        secuenciaJugador.add(buttonId)
        comprobacion(context)

    }
    private fun comprobacion(context: Context) {
        if (secuenciaJugador.size <= secuenciaMaquina.size) {
            comprobarSecuencia(context)
        }
    }
    private fun comprobarSecuencia(context: Context) {
        if (secuenciaMaquina == secuenciaJugador){
            secuenciaJugador.clear()
            Log.d("TAG", "CORRECTO")
            setToastText("Ronda " + ronda.value + " superada")
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            generarSecuencia()
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
            setToastText("Ronda perdida :(")
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            estadoEsperando()
        }
    }

    private fun setToastText(text: String) {
        toastText = text
    }

    private fun estadoGenerando() {
        estadoLiveData.value = GENERANDO
    }

    fun estadoJugando() {
        estadoLiveData.value = JUGANDO
    }

    private fun estadoEsperando() {
        estadoLiveData.value = ESPERANDO
    }

//    fun getRonda(): Int {
//        return Datos.ronda.value
//    }
//
//    fun getSecuenciaMaquina(): MutableList<Int> {
//        return Datos.secuenciaMaquina
//    }
//
//    fun getSecuenciaJugador(): MutableList<Int> {
//        return Datos.secuenciaJugador
//    }


}