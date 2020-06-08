package com.inspirecoding.retrofitdemo.repository

import com.inspirecoding.retrofitdemo.model.User

class UserRepository
{
    suspend fun getUser(email: String): User
    {
        return RetrofitClient.instance.getUser(email)[0]
    }
    suspend fun getAllUsers(): List<User>
    {
        return RetrofitClient.instance.getAllUser()
    }
    suspend fun registerUser(email: String, name: String, password: String): User
    {
        return RetrofitClient.instance.registerUser(email, name, password)
    }
}