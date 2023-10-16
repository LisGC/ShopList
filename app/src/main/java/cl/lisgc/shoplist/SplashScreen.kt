package cl.lisgc.shoplist

import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.window.SplashScreenView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var max: Long = 2500
        var x: Long = 500

        val timer = object : CountDownTimer(max,x) {
            override fun onTick(millisUntilFinished: Long) {
                var x: Long = max - millisUntilFinished
            }

            override fun onFinish() {
                var intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)


            }
        }
        timer.start()
    }
}