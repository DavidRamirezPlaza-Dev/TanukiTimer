package com.DRPDev.tanukitimer.Principales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.DRPDev.tanukitimer.R

class Modo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modo)

        val eleccion = intent.getStringExtra("eleccion")

        val btnTorneo = findViewById<ImageButton>(R.id.btnTorneo)
        btnTorneo.setOnClickListener {
            when (eleccion) {
                "mitos" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Mitos.MesaMitos::class.java)
                    startActivity(intent)
                }
                "vanguard" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Vanguard.mesaVanguard::class.java)
                    startActivity(intent)
                }
                "pokemon" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Pokemon.mesaPokemon::class.java)
                    startActivity(intent)
                }
                "furia" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Mitos.MesaMitosFuria::class.java)
                    startActivity(intent)
                }
            }
        }
        val btnCasual = findViewById<ImageButton>(R.id.btnCasual)
        btnCasual.setOnClickListener {
            when (eleccion) {
                "mitos" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Mitos.timerCasual::class.java)
                    startActivity(intent)
                }
                "vanguard" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Vanguard.timerVanguardCasual::class.java)
                    startActivity(intent)
                }
                "pokemon" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Pokemon.timerPokemonCasual::class.java)
                    startActivity(intent)
                }
                "furia" -> {
                    val intent = Intent(this, com.DRPDev.tanukitimer.Mitos.timerCasualFuria::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}