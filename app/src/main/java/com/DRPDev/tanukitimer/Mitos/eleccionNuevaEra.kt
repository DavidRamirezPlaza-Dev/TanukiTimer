package com.DRPDev.tanukitimer.Mitos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.DRPDev.tanukitimer.R

class eleccionNuevaEra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_nueva_era)

        val btnFuria = findViewById<ImageButton>(R.id.btnFuria)
        btnFuria.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Principales.Modo::class.java)
            intent.putExtra("eleccion", "furia")
            startActivity(intent)
        }
        val btnImperio = findViewById<ImageButton>(R.id.btnImperio)
        btnImperio.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Principales.Modo::class.java)
            intent.putExtra("eleccion", "mitos")
            startActivity(intent)
        }

    }
}