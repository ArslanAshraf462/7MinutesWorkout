package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //timer variable
    private var countDownTimer : CountDownTimer? = null
    // duration of timer in milliseconds
    private var timerDuration : Long = 60000
    // pauseOffset = timerDuration - time left
    private var pauseOffset : Long = 0
    val tvTimer : TextView = findViewById(R.id.tvTimer)
    val btnStart : Button = findViewById(R.id.btnStart)
    val btnPause : Button = findViewById(R.id.btnPause)
    val btnStop : Button = findViewById(R.id.btnStop)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer.text = (timerDuration/1000).toString()

        btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }

        btnPause.setOnClickListener {
            pauseTimer()
        }

        btnStop.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        if (countDownTimer != null){
            countDownTimer!!.cancel()
            tvTimer.text = (timerDuration/1000).toString()
            countDownTimer = null
            pauseOffset = 0
        }
    }

    private fun pauseTimer() {
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
        }
    }

    private fun startTimer(pauseOffset: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffset, 1000){
            override fun onTick(millisUntilFinished: Long) {
                this@MainActivity.pauseOffset = timerDuration - millisUntilFinished
                tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@MainActivity,
                    "Timer is finished",
                    Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}