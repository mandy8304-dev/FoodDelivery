package com.oded.food.delivery.admin.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.oded.food.delivery.admin.databinding.ActivitySplashscreenBinding

class SplashScreenActivity : BaseActivity()  {

    private lateinit var binding: ActivitySplashscreenBinding

    companion object {
        fun intent(context: Context) : Intent {
            return Intent(context, SplashScreenActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({ check() }, 2000)
    }

    private fun check() {

        val ii =  if(currentUser != null){
            MainActivity.intent(this@SplashScreenActivity)
        } else {
            ActivityLogin.intent(this@SplashScreenActivity)
        }
        startActivity(ii)
        finish()
    }
}