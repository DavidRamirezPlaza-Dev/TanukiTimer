package com.DRPDev.tanukitimer.Mitos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.DRPDev.tanukitimer.R

class mitosPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitos_principal)

        val btnPBX = findViewById<ImageButton>(R.id.btnPBX)
        btnPBX.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Principales.Modo::class.java)
            intent.putExtra("eleccion","mitos")
            startActivity(intent)
        }
        val btnNuevaEra = findViewById<ImageButton>(R.id.btnNuevaEra)
        btnNuevaEra.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Mitos.eleccionNuevaEra::class.java)
            startActivity(intent)
        }

    }
}