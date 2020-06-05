package com.inspirecoding.retrofitdemo.repository

import com.inspirecoding.retrofitdemo.model.User

class UserRepository
{
    suspend fun registerUser(email: String, name: String, password: String): User
    {
        return RetrofitClient.instance.registerUser(email, name, password)
    }
}