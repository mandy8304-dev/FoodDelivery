package com.oded.food.delivery.admin.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oded.food.delivery.admin.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun intent(context: Context) : Intent {
            return Intent(context, ActivityLogin::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}