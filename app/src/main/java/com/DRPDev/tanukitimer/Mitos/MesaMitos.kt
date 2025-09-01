package com.DRPDev.tanukitimer.Mitos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.DRPDev.tanukitimer.R

class MesaMitos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesa_mitos)



        val btnAceptar = findViewById<ImageButton>(R.id.btnAceptarMesaMitos)
        btnAceptar.setOnClickListener {
            val numeroMesa = findViewById<EditText>(R.id.numeroMesaMitos).text.toString()
            val jugador1Mitos = findViewById<EditText>(R.id.jugador1mitos).text.toString()
            val jugador2Mitos = findViewById<EditText>(R.id.jugador2mitos).text.toString()
            if (numeroMesa.isEmpty()){
                mensajeVacio()
                return@setOnClickListener
            }else if(jugador1Mitos.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }else if(jugador2Mitos.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }
            else{
                val intent = Intent(this, timerTorneo::class.java)
                intent.putExtra("numeroMesa",numeroMesa)
                intent.putExtra("jugador1Mitos", jugador1Mitos)
                intent.putExtra("jugador2Mitos", jugador2Mitos)
                println(numeroMesa)
                startActivity(intent)
            }
        }

    }
    private fun mensajeVacio(){
        Toast.makeText(baseContext, "Ingresa el número de mesa", Toast.LENGTH_SHORT).show()
    }
    private fun mensajeVacioJugador(){
        Toast.makeText(baseContext, "Ingresa el nombre de los jugadores", Toast.LENGTH_SHORT).show()
    }
}