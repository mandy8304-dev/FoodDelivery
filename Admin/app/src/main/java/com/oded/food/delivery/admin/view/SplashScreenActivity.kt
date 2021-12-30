package com.oded.food.delivery.admin.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.oded.food.delivery.admin.common.Preferences
import com.oded.food.delivery.admin.common.Utils
import com.oded.food.delivery.admin.databinding.ActivitySplashscreenBinding

class SplashScreenActivity : AppCompatActivity()  {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({ check() }, 2000)
    }


    private fun check() {

//        val ii = if (Preferences.getBooleanPreference(this@SplashScreenActivity, Utils.SESSION, false)) {
//            MainActivity.intent(this@SplashScreenActivity)
//        } else {
//             ActivityLogin.intent(this@SplashScreenActivity)
//        }

        startActivity(/*ii*/MainActivity.intent(this@SplashScreenActivity))
        finish()
    }
}