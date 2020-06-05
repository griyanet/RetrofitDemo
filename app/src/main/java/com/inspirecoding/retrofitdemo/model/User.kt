package com.inspirecoding.retrofitdemo.model

data class User (
    var userId: Int = 0,
    var name: String = "",
    var email: String = "",
    var password: String = ""
)