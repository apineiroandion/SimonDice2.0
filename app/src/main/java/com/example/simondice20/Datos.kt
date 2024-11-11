package com.example.simondice20

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

object Datos {
    val record = mutableStateOf(0)
    var ronda = mutableStateOf(0)
    val secuenciaMaquina = mutableListOf<Int>()
    val secuenciaJugador = mutableListOf<Int>()
    var toastText = ""
    val isPrinted = mutableStateOf(false)


}

/**
 * Colores
 */
enum class Colors (val id: Int, val nombre: String, val color: Color, val colorPressed: Color){
    RED(0, "Red", Color(0xFFE57373), Color(0xFFB71C1C)),
    BLUE(1, "Blue", Color(0xFF64B5F6), Color(0xFF0D47A1)),
    GREEN(2, "Green", Color(0xFF81C784), Color(0xFF1B5E20)),
    YELLOW(3, "Yellow", Color(0xFFFFF176), Color(0xFFF57F17));
}

/**
 * Estados
 */
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean, val colorearSecuencia: Boolean) {
    ESPERANDO(start_activo = true, boton_activo = false, colorearSecuencia = false),
    GENERANDO(start_activo = false, boton_activo = true, colorearSecuencia = true),
    JUGANDO(start_activo = false, boton_activo = true, colorearSecuencia = false),
}