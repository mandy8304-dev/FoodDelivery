package com.oded.food.delivery.admin.logic.impl

import com.google.firebase.auth.FirebaseAuth

interface IApplication {

    fun auth() : FirebaseAuth
}