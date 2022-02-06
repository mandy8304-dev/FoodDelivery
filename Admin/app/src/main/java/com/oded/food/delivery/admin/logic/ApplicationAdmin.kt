package com.oded.food.delivery.admin.logic

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.oded.food.delivery.admin.logic.impl.IApplication

class ApplicationAdmin : Application(), IApplication {

    private lateinit var auth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()
        this.auth = Firebase.auth
    }

    override fun auth(): FirebaseAuth {
        return this.auth
    }
}