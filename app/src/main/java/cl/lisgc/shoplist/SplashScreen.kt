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

        val max: Long = 2500
        val x: Long = 500

        val timer = object : CountDownTimer(max,x) {
            override fun onTick(millisUntilFinished: Long) {
                var x: Long = max - millisUntilFinished
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)


            }
        }
        timer.start()
    }
}