package com.example.simondice20

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Singleton para almacenar los datos de la aplicación
 */
object Datos {
    /**
     * Recor para implementar en la base de datos
     */
    val record = mutableStateOf(0)
    /**
     * Ronda actual
     */
    var ronda = mutableStateOf(0)
    /**
     * Estado actual de la combinacion a adivinar
     */
    val secuenciaMaquina = mutableListOf<Int>()
    /**
     * Estado actual de la combinacion del jugador
     */
    val secuenciaJugador = mutableListOf<Int>()
    /**
     * Texto que se muestra en el toast
     */
    var toastText = ""
    /**
     * Color actual
     */
    val isPrinted = mutableStateOf(false)


}

/**
 * Clase enum para los colores
 */
enum class Colors (val id: Int, val nombre: String, val color: Color, val colorPressed: Color){
    RED(0, "Red", Color(0xFFE57373), Color(0xFFB71C1C)),
    BLUE(1, "Blue", Color(0xFF64B5F6), Color(0xFF0D47A1)),
    GREEN(2, "Green", Color(0xFF81C784), Color(0xFF1B5E20)),
    YELLOW(3, "Yellow", Color(0xFFFFF176), Color(0xFFF57F17));
}

/**
 * Clase enum para los estados
 */
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean, val colorearSecuencia: Boolean) {
    ESPERANDO(start_activo = true, boton_activo = false, colorearSecuencia = false),
    GENERANDO(start_activo = false, boton_activo = true, colorearSecuencia = true),
    JUGANDO(start_activo = false, boton_activo = true, colorearSecuencia = false),
}