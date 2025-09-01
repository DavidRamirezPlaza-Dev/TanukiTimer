package com.DRPDev.tanukitimer.Mitos

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.DRPDev.tanukitimer.R

class MesaMitosFuria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesa_mitos_furia)

        val btnAceptar = findViewById<ImageButton>(R.id.btnAceptarMesaMitosFuria)
        btnAceptar.setOnClickListener {
            val numeroMesa = findViewById<EditText>(R.id.numeroMesaMitosFuria).text.toString()
            val jugador1MitosFuria = findViewById<EditText>(R.id.jugador1MitosFuria).text.toString()
            val jugador2MitosFuria = findViewById<EditText>(R.id.jugador2mitosFuria).text.toString()
            if (numeroMesa.isEmpty()){
                mensajeVacio()
                return@setOnClickListener
            }else if (jugador1MitosFuria.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }else if (jugador2MitosFuria.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }
            else{
                val intent = Intent(this, timerTorneoFuria::class.java)
                intent.putExtra("numeroMesa",numeroMesa)
                intent.putExtra("jugador1MitosFuria", jugador1MitosFuria)
                intent.putExtra("jugador2MitosFuria", jugador2MitosFuria)
                println(numeroMesa)
                startActivity(intent)
            }
        }

    }
    private fun mensajeVacio(){
        Toast.makeText(baseContext, "Ingresa el número de mesa", Toast.LENGTH_SHORT).show()
    }
    private fun mensajeVacioJugador(){
        Toast.makeText(baseContext, "Ingrese el nombre de los jugadores", Toast.LENGTH_SHORT).show()
    }
}