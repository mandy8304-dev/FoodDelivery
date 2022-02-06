package com.oded.food.delivery.admin.model

data class DProvider(
    var photo: String,
    var name: String,
    var lastname: String,
    var genero: String = "M",
    var email: String,
    var username: String,
    var status: String,
)
