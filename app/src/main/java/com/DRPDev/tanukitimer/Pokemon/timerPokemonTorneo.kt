package com.DRPDev.tanukitimer.Pokemon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.os.CountDownTimer
import com.DRPDev.tanukitimer.R
import android.speech.tts.TextToSpeech
import android.widget.EditText
import android.widget.ImageButton
import java.util.*

class timerPokemonTorneo : AppCompatActivity(), TextToSpeech.OnInitListener{

    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textToSpeech: TextToSpeech
    private var timerRunning: Boolean = false
    private var currentStep: Int = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_pokemon_torneo)

        timerTextView = findViewById(R.id.timerTextViewPokemonT)
        val btnComenzarMesaPokemonTorneo = findViewById<ImageButton>(R.id.btnComenzarMesaPokemonT)
        btnComenzarMesaPokemonTorneo.setOnClickListener {
            if(!timerRunning){
                speak("3 minutos para barajar")
                startTimer()
            }
        }

        textToSpeech = TextToSpeech(this, this)

        val numeroMesaPokemon = intent.getStringExtra("numeroMesaPokemon")
        val jugador1Pokemon = intent.getStringExtra("jugador1Pokemon")
        val jugador2Pokemon = intent.getStringExtra("jugador2Pokemon")
        findViewById<EditText>(R.id.mesaPokemon).setText("Mesa: $numeroMesaPokemon")
        findViewById<EditText>(R.id.jugador1PokemonTorneo).setText("Jugador 1: $jugador1Pokemon")
        findViewById<EditText>(R.id.jugador2PokemonTorneo).setText("Jugador 2: $jugador2Pokemon")

    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }
    //Conección a la librería
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.getDefault())
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                println("Idioma Soportado")
            }
        } else {
            println("Error")
        }
    }

    private fun startTimer() {
        when (currentStep) {
            1 -> startStepTimer(3 * 60 * 1000) // Paso 1: 3 minuto
            2 ->{
                speak("La partida ha comenzado")
                startStepTimer(50 * 60 * 1000)
            } // Paso 2: 45 minutos
        }
    }

    private fun startStepTimer(duration: Long) {
        countDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)


                // Emitir voz cuando el temporizador llegue al valor específico
                if (currentStep == 2 && timerTextView.text == "05:00") {
                    speak("Quedan 5 minutos")
                }
                if (currentStep == 2 && timerTextView.text == "25:00"){
                    speak("Quedan 25 minutos")
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                if (currentStep == 1) {
                    // Pasar al paso 2
                    currentStep = 2
                    startTimer()
                } else if (currentStep == 2) {
                    timerTextView.text ="00:00"
                    timerRunning = false
                    speak("3 turnos")
                }
            }
        }
        countDownTimer.start()
        timerRunning = true
    }

    private fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}