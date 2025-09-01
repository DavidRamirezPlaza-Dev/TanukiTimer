package com.DRPDev.tanukitimer.Pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.DRPDev.tanukitimer.R

class mesaPokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesa_pokemon)

        val btnAceptarPokemon = findViewById<ImageButton>(R.id.btnAceptarMesaPokemon)
        btnAceptarPokemon.setOnClickListener {
            val numeroMesaPokemon = findViewById<EditText>(R.id.numeroMesaPokemon).text.toString()
            val jugador1Pokemon = findViewById<EditText>(R.id.jugador1Pokemon).text.toString()
            val jugador2Pokemon = findViewById<EditText>(R.id.jugador2Pokemon).text.toString()
            if (numeroMesaPokemon.isEmpty()){
                mensajeVacio()
                return@setOnClickListener
            }else if (jugador1Pokemon.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }else if (jugador2Pokemon.isEmpty()){
                mensajeVacioJugador()
                return@setOnClickListener
            }
            else{
                val intent = Intent(this, com.DRPDev.tanukitimer.Pokemon.timerPokemonTorneo::class.java)
                intent.putExtra("numeroMesaPokemon",numeroMesaPokemon)
                intent.putExtra("jugador1Pokemon", jugador1Pokemon)
                intent.putExtra("jugador2Pokemon", jugador2Pokemon)
                startActivity(intent)
            }
        }
    }
    private fun mensajeVacio() {
        Toast.makeText(baseContext, "Ingresa el número de mesa", Toast.LENGTH_SHORT).show()
    }
    private fun mensajeVacioJugador(){
        Toast.makeText(baseContext, "Ingresa el nombre de los jugadores",Toast.LENGTH_SHORT).show()
    }
}