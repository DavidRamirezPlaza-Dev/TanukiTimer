package com.DRPDev.tanukitimer.Mitos

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.DRPDev.tanukitimer.R
import java.util.Locale

class timerTorneoFuria : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textToSpeech: TextToSpeech
    private var timerRunning: Boolean = false
    private var currentStep: Int = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_torneo_furia)

        timerTextView = findViewById(R.id.timerTextViewFuria)
        val btnComenzarMesaMitosTF = findViewById<ImageButton>(R.id.btnComenzarMesaMitosTF)
        btnComenzarMesaMitosTF.setOnClickListener {
            if (!timerRunning) {
                speak("3 minutos para barajar")
                startTimer()
            }
        }

        textToSpeech = TextToSpeech(this, this)

        val numeroMesaFuria = intent.getStringExtra("numeroMesa")
        val jugador1MitosFuria = intent.getStringExtra("jugador1MitosFuria")
        val jugador2MitosFuria = intent.getStringExtra("jugador2MitosFuria")
        findViewById<EditText>(R.id.mesaFuria).setText("Mesa: $numeroMesaFuria")
        findViewById<EditText>(R.id.jugador1MitosTorneoFuria).setText("Jugador 1: $jugador1MitosFuria")
        findViewById<EditText>(R.id.jugador2MitosTorneoFuria).setText("Jugador 2: $jugador2MitosFuria")

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
                startStepTimer(40 * 60 * 1000)
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
                if (currentStep == 2 && timerTextView.text == "20:00"){
                    speak("Quedan 20 minutos")
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