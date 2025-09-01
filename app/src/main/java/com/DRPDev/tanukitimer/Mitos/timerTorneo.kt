package com.DRPDev.tanukitimer.Mitos

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



class timerTorneo : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textToSpeech: TextToSpeech
    private var timerRunning: Boolean = false
    private var currentStep: Int = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_torneo)



        timerTextView = findViewById(R.id.timerTextView)
        val btnComenzarMesaMitosT = findViewById<ImageButton>(R.id.btnComenzarMesaMitosT)
        btnComenzarMesaMitosT.setOnClickListener {
            if (!timerRunning) {
                speak("3 minutos para barajar")
                startTimer()
            }
        }

        textToSpeech = TextToSpeech(this, this)

        val numeroMesa = intent.getStringExtra("numeroMesa")
        val jugador1Mitos = intent.getStringExtra("jugador1Mitos")
        val jugador2Mitos = intent.getStringExtra("jugador2Mitos")
        findViewById<EditText>(R.id.mesa).setText("Mesa: $numeroMesa")
        findViewById<EditText>(R.id.jugador1MitosTorneo).setText("Jugador 1: $jugador1Mitos")
        findViewById<EditText>(R.id.jugador2MitosTorneo).setText("Jugador 2: $jugador2Mitos")

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
                startStepTimer(45 * 60 * 1000)
            } // Paso 2: 45 minutos
            3 -> {
                speak("5 minutos adicionales")
                startStepTimer(5 * 60 * 1000)} // Paso 3: 5 minutos
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
                when (currentStep) {
                    1 -> {
                        // Pasar al paso 2
                        currentStep = 2
                        startTimer()
                    }
                    2 -> {
                        // Pasar al paso 3
                        currentStep = 3
                        startTimer()
                    }
                    3 -> {
                        // Finalizar
                        timerTextView.text = "00:00"
                        timerRunning = false
                        speak("La partida ha terminado")
                        speak("Jugador actual termina la partida")
                    }
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