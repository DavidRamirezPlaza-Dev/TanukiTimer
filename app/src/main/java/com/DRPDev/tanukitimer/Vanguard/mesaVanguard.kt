package com.DRPDev.tanukitimer.Vanguard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.DRPDev.tanukitimer.R

class mesaVanguard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesa_vanguard)

        val btnAceptarVanguard = findViewById<ImageButton>(R.id.btnAceptarMesaVanguard)
        btnAceptarVanguard.setOnClickListener {
            val numeroMesaVanguard = findViewById<EditText>(R.id.numeroMesaVanguard).text.toString()
            val jugador1Vanguard = findViewById<EditText>(R.id.jugador1Vanguard).text.toString()
            val jugador2Vanguard = findViewById<EditText>(R.id.jugador2Vanguard).text.toString()
            if (numeroMesaVanguard.isEmpty()){
                mensajeVacio()
                return@setOnClickListener
            }else if (jugador1Vanguard.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }else if(jugador2Vanguard.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }
            else{
                val intent = Intent(this, timerVanguardTorneo::class.java)
                intent.putExtra("numeroMesaVanguard", numeroMesaVanguard)
                intent.putExtra("jugador1Vanguard", jugador1Vanguard)
                intent.putExtra("jugador2Vanguard", jugador2Vanguard)
                startActivity(intent)
            }
        }
    }
    private fun mensajeVacio() {
        Toast.makeText(baseContext, "Ingresa el número de mesa", Toast.LENGTH_SHORT).show()
    }
    private fun mensajeVacioJugador(){
        Toast.makeText(baseContext, "Ingrese los jugadores", Toast.LENGTH_SHORT).show()
    }

}