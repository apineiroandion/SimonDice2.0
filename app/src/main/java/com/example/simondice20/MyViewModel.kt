package com.example.simondice20

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.window.application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondice20.Datos.isPrinted
import com.example.simondice20.Datos.ronda
import com.example.simondice20.Datos.secuenciaJugador
import com.example.simondice20.Datos.secuenciaMaquina
import com.example.simondice20.Datos.toastText
import com.example.simondice20.Estados.*
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {
    var estadoLiveData: MutableLiveData<Estados> = MutableLiveData(ESPERANDO)
    private val recordDao = RecordDatabase.getDatabase(application).recordDao()

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

    fun saveRecord(value: Int) {
        viewModelScope.launch {
            recordDao.insert(Record(value = value))
        }
    }

    fun getRecord(callback: (Record?) -> Unit) {
        viewModelScope.launch {
            val record = recordDao.getRecord()
            callback(record)
        }
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