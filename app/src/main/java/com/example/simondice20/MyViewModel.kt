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

/**
 * Clase ViewModel para la lógica de la aplicación
 */
class MyViewModel: ViewModel() {
    /**
     * LiveData para el estado de la aplicación
     */
    var estadoLiveData: MutableLiveData<Estados> = MutableLiveData(ESPERANDO)

    /**
     * Función para aumentar la ronda
     */
    private fun aumentarRonda() {
        ronda.value += 1
    }

    /**
     * Función para generar la secuencia de la máquina
     */
    fun generarSecuencia() {
        aumentarRonda()
        secuenciaMaquina.add((0..3).random())
        isPrinted.value = false
        estadoGenerando()
    }

    /**
     * Función que inicia la comprobación de la secuencia
     */
    fun click(buttonId: Int, context: Context) {
        secuenciaJugador.add(buttonId)
        comprobacion(context)

    }

    /**
     * Funcion que lanza la comprobacion si la secuecia jugador es igual o menor
     * a la secuencia maquina
     */
    private fun comprobacion(context: Context) {
        if (secuenciaJugador.size <= secuenciaMaquina.size) {
            comprobarSecuencia(context)
        }
    }

    /**
     * Función para comprobar si la secuencia del jugador es correcta
     */
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

    /**
     * Función para establecer el texto del toast
     */
    private fun setToastText(text: String) {
        toastText = text
    }

    /**
     * Función para establecer el estado de la aplicación a GENERANDO
     */
    private fun estadoGenerando() {
        estadoLiveData.value = GENERANDO
    }

    /**
     * Función para establecer el estado de la aplicación a JUGANDO
     */
    fun estadoJugando() {
        estadoLiveData.value = JUGANDO
    }

    /**
     * Función para establecer el estado de la aplicación a ESPERANDO
     */
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