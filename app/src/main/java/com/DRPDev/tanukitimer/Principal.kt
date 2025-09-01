package com.DRPDev.tanukitimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.DRPDev.tanukitimer.Mitos.mitosPrincipal
import android.net.Uri


class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_principal)
        val btnMitos = findViewById<ImageButton>(R.id.btnMitos)
        btnMitos.setOnClickListener {
            val intent = Intent(this, mitosPrincipal::class.java)
            startActivity(intent)
        }
        val btnVanguard = findViewById<ImageButton>(R.id.btnVanguard)
        btnVanguard.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Principales.Modo::class.java)
            intent.putExtra("eleccion", "vanguard")
            startActivity(intent)
        }
        val btnPokemon = findViewById<ImageButton>(R.id.btnPokemon)
        btnPokemon.setOnClickListener {
            val intent = Intent(this, com.DRPDev.tanukitimer.Principales.Modo::class.java)
            intent.putExtra("eleccion", "pokemon")
            startActivity(intent)
        }
        //link a web
        val link = findViewById<TextView>(R.id.link)
        link.setOnClickListener {
            val url = "https://tanukipuq.cl"
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            if (intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }
    }

}